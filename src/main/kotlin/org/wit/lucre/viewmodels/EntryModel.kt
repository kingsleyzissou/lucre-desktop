package org.wit.lucre.viewmodels

import org.wit.lucre.models.Entry
import tornadofx.ItemViewModel

/**
 * TornadoFX ViewModel for corresponding Entry model
 */
class EntryModel(entry: Entry) : ItemViewModel<Entry>(entry) {
    val amount = bind(Entry::amountProperty)
    val type = bind(Entry::typeProperty)
    val vendor = bind(Entry::vendorProperty)
    val description = bind(Entry::descProperty)
    val category = bind(Entry::categoryProperty)
    val vault = bind(Entry::vaultProperty)
    val date = bind(Entry::dateProperty)
}
