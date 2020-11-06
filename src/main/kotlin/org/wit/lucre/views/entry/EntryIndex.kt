package org.wit.lucre.views.entry

import javafx.collections.FXCollections
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.events.EntriesFilterEvent
import org.wit.lucre.events.SwitchScenesRequest
import org.wit.lucre.models.Entry
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.*

class EntryIndex : Fragment("List Entries") {
    private val entryController: EntryController by inject()
    val vault: VaultModel by inject()
    val model = EntryModel(Entry())

    val entries = FXCollections.observableArrayList<Entry>()

    override val root = tableview(entries) {
        readonlyColumn("Vendor", Entry::vendor)
        readonlyColumn("Description", Entry::description)
        readonlyColumn("Category", Entry::category)
        readonlyColumn("Amount (${vault.item.currency})", Entry::signedAmount)
        readonlyColumn("Date", Entry::simpleDate)
        bindSelected(model)

        contextmenu {
            item("Edit").action {
                if (selectedItem != null) {
                    switch(selectedItem!!, "edit")
                }
            }
            item("Delete").action {
                if (selectedItem != null) {
                    switch(selectedItem!!, "delete")
                }
            }
        }

        subscribe<EntriesFilterEvent> {
            entries.setAll(it.entries)
        }
    }

    override fun onDock() {
        super.onDock()
        subscribe<EntriesFilterEvent> {
            entries.setAll(it.entries)
        }
    }

    private fun switch(selectedItem: Entry, action: String) {
        val scope = Scope()
        setInScope(EntryModel(selectedItem), scope)
        val view = when (action) {
            "delete" -> find(EntryDelete::class, scope)
            else -> find(EntryEdit::class, scope)
        }
        fire(SwitchScenesRequest(view, ViewTransition.Fade(0.4.seconds)))
    }
}
