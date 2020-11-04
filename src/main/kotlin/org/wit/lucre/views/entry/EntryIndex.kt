package org.wit.lucre.views.entry

import org.wit.lucre.events.EntriesFilterEvent
import org.wit.lucre.models.Entry
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.*

class EntryIndex : Fragment("List Entries") {
    val vault: VaultModel by inject()
    val model = EntryModel(Entry())

    override val root = tableview<Entry> {
        readonlyColumn("Vendor", Entry::vendor)
        readonlyColumn("Description", Entry::description)
        readonlyColumn("Category", Entry::category)
        readonlyColumn("Amount (${vault.item.currency})", Entry::amount)
        bindSelected(model)

        subscribe<EntriesFilterEvent> { event ->
            val entries = event.entries.toObservable()
            items.setAll(entries)
        }
    }
}
