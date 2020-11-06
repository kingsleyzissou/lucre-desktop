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
import java.time.LocalDate
import java.util.*
import java.util.function.Predicate

class VaultShow : Fragment("Show Vault") {
    val model: VaultModel by inject()
    private val entryController: EntryController by inject()

    private val entryView = getEntryIndexView()
    private val chartView = getChartView()

    private var date = LocalDate.now()
    private var label = label(date.month.toString())

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
                    text = "${model.name.value} (${model.currency.value}$balance)"
                }
            }
            hbox {
                this += chartView.root
            }
            hbox(spacing = 20) {
                alignment = Pos.CENTER
                button("<").action { changeDate(-1) }
                this += label
                button(">").action { changeDate(+1) }
                hboxConstraints {
                    marginBottom = 20.0
                }
            }
        }
        center = entryView.root
    }

    /**
     * Method called once the fragment
     * has loaded
     */
    override fun onDock() {
        subscribeToEntryRequest()
        subscribeToSceneSwitch()
        subscribeToLoadEntries()
        fire(LoadEntriesRequest())
    }

    /**
     * Used for subscribing to requests
     * for new entries, used for syncing data between
     * parent and child view components
     */
    private fun subscribeToEntryRequest() {
        subscribe<EntriesFilterRequest> {
            val entries = entryController.filter(it.predicate)
            fire(EntriesFilterEvent(entries))
        }
    }

    /**
     * Used for subscribing to requests
     * for switching scenes, used for syncing data between
     * parent and child view components
     */
    private fun subscribeToSceneSwitch() {
        subscribe<SwitchScenesRequest> {
            switch(it.fragment, it.transition)
        }
    }

    /**
     * Used for subscribing to new entries being returned
     * from the controller. Used for syncing data between
     * parent and child view components
     */
    private fun subscribeToLoadEntries() {
        subscribe<LoadEntriesRequest> {
            val predicate = Predicate<Entry> {
                it.vault == model.item.id &&
                    it.date.year == date.year &&
                    it.date.month == date.month
            }
            fire(EntriesFilterRequest(predicate))
        }
    }

    /**
     * Method for updating the date
     * and loading new entries based
     * on the date change
     */
    private fun changeDate(value: Long) {
        date = date.plusMonths(value)
        label.text = date.month.toString()
        fire(LoadEntriesRequest())
    }

    /**
     * Load the Entry Index fragment
     */
    private fun getEntryIndexView(): EntryIndex {
        val v = VaultModel(model.item)
        val scope = Scope()
        setInScope(v, scope)
        return find(EntryIndex::class, scope)
    }

    /**
     * Get the Entry Chart fragment
     */
    private fun getChartView(): EntryChart {
        val v = VaultModel(model.item)
        val scope = Scope()
        setInScope(v, scope)
        return find(EntryChart::class, scope)
    }

    /**
     * Method for switching to another fragment
     */
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

    /**
     * Method for switching to another fragment
     */
    private fun switch(fragment: Fragment, transition: ViewTransition) {
        replaceWith(fragment, transition)
    }
}
