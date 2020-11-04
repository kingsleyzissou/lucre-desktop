package org.wit.lucre.views.vault

import javafx.scene.text.Font
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.events.EntriesFilterEvent
import org.wit.lucre.events.EntriesFilterRequest
import org.wit.lucre.models.Entry
import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.entry.EntryChart
import org.wit.lucre.views.entry.EntryIndex
import tornadofx.*
import java.util.function.Predicate

class VaultShow : Fragment("Show Vault") {
    val model: VaultModel by inject()
    private val entryController: EntryController by inject()

    private val entryView = getEntryIndexView()
    private val chartView = getChartView()

    override val root = borderpane {
        top = vbox {
            text(model.name) {
                font = Font(20.0)
            }
            hbox {
                this += chartView.root
            }
        }
        center = entryView.root
    }

    override fun onDock() {
        subscribe<EntriesFilterRequest> {
            val entries = entryController.filter(it.predicate)
            fire(EntriesFilterEvent(entries))
        }
        var predicate = Predicate<Entry> { p -> p.vault == model.item.id }
        fire(EntriesFilterRequest(predicate))
    }

    private fun getEntryIndexView(): EntryIndex {
        val v = VaultModel(model.item)
        val scope = Scope()
        setInScope(v, scope)
        return find(EntryIndex::class, scope)
    }

    private fun getChartView(): EntryChart {
        val v = VaultModel(model.item)
        val scope = Scope()
        setInScope(v, scope)
        return find(EntryChart::class, scope)
    }
}
