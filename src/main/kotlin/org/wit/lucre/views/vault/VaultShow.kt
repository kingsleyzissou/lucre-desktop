package org.wit.lucre.views.vault

import javafx.scene.text.Font
import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.entry.EntryIndex
import tornadofx.*

class VaultShow : Fragment("Show Vault") {
    val model: VaultModel by inject()

    private val entryView = getEntryIndexView()

    override val root = borderpane {
        top = vbox {
            text(model.name) {
                font = Font(20.0)
            }
        }
        center = entryView.root
    }

    private fun getEntryIndexView(): EntryIndex {
        val v = VaultModel(model.item)
        val scope = Scope()
        setInScope(v, scope)
        return find(EntryIndex::class, scope)
    }
}
