package org.wit.lucre.views.entry

import org.wit.lucre.controllers.EntryController
import org.wit.lucre.models.Entry
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.* // ktlint-disable no-wildcard-imports

class EntryIndex : Fragment("List Entries") {
    val vault: VaultModel by inject()
    val model = EntryModel(Entry())
    private val entryController: EntryController by inject()
    private val data = entryController.index().asObservable()

    override val root = tableview(data) {
        readonlyColumn("Vendor", Entry::vendor)
        readonlyColumn("Description", Entry::description)
        readonlyColumn("Category", Entry::category)
        readonlyColumn("Amount (${vault.item.currency})", Entry::amount)
        bindSelected(model)
    }
}
