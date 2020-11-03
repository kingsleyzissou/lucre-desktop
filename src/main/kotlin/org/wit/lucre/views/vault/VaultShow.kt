package org.wit.lucre.views.vault

import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.entry.EntryIndex
import tornadofx.* // ktlint-disable no-wildcard-imports

class VaultShow : Fragment("Show Vault") {
    val model: VaultModel by inject()

    private val entryView = getEntryIndexView()

    override val root = borderpane {
        top = label(model.name)
        center = entryView.root
    }

    private fun getEntryIndexView(): EntryIndex {
        val v = VaultModel(model.item)
        val scope = Scope()
        setInScope(v, scope)
        return find(EntryIndex::class, scope)
    }
}
