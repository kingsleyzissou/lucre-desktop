package org.wit.lucre.views.vault

import javafx.geometry.Pos
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.Vault
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.*

class VaultIndex : Fragment("Vault List") {
    private val vaultController: VaultController by inject()
    private val vaults = vaultController.index()

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Create") {
                    action { switch(null, "create") }
                }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
        }
        center = datagrid(vaults) {
            cellCache {
                label(it.name)
            }
            onUserSelect(2) { switch(it, "show") }
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
        }
    }

    private fun switch(vault: Vault?, action: String) {
        val scope = Scope()
        if (vault != null) {
            val model = VaultModel(vault)
            setInScope(model, scope)
        }
        var view = when (action) {
            "show" -> find(VaultShow::class, scope)
            "edit" -> find(VaultEdit::class, scope)
            "delete" -> find(VaultDelete::class, scope)
            else -> find(VaultCreate::class, scope)
        }
        replaceWith(view, ViewTransition.FadeThrough(0.5.seconds))
    }
}
