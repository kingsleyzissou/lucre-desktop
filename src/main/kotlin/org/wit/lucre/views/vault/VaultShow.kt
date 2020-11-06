package org.wit.lucre.views.vault

import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.events.EntriesFilterEvent
import org.wit.lucre.events.EntriesFilterRequest
import org.wit.lucre.events.LoadEntriesRequest
import org.wit.lucre.events.SwitchScenesRequest
import org.wit.lucre.models.Entry
import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.entry.EntryChart
import org.wit.lucre.views.entry.EntryCreate
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
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Back").action { switch("") }
                button("Edit").action { switch("edit") }
                button("Add Item").action { switch("create") }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text {
                font = Font(20.0)
                subscribe<EntriesFilterEvent> {
                    val balance = entryController.balance(it.entries)
                    text = "${model.name.value} (${model.currency.value}${balance})"
                }
            }
            hbox {
                this += chartView.root
            }
        }
        center = entryView.root
    }

    override fun onDock() {
        subscribeToEntryRequest()
        subscribeToSceneSwitch()
        subscribeToLoadEntries()
        fire(LoadEntriesRequest())
    }

    private fun subscribeToEntryRequest() {
        subscribe<EntriesFilterRequest> {
            val entries = entryController.filter(it.predicate)
            fire(EntriesFilterEvent(entries))
        }
    }

    private fun subscribeToSceneSwitch() {
        subscribe<SwitchScenesRequest> {
            switch(it.fragment, it.transition)
        }
    }

    private fun subscribeToLoadEntries() {
        subscribe<LoadEntriesRequest> {
            val predicate = Predicate<Entry> { p -> p.vault == model.item.id }
            fire(EntriesFilterRequest(predicate))
        }
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

    private fun switch(action: String?) {
        val scope = Scope()
        setInScope(model, scope)
        val view = when (action) {
            ("edit") -> find(VaultEdit::class, scope)
            ("create") -> find(EntryCreate::class, scope)
            else -> find(VaultIndex::class)
        }
        replaceWith(view, ViewTransition.Slide(0.2.seconds))
    }

    private fun switch(fragment: Fragment, transition: ViewTransition) {
        replaceWith(fragment, transition)
    }
}
