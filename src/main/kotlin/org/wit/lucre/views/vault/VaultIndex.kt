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
                    action { switch(null) }
                }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
        }
        center = datagrid(vaults) {
            cellCache {
                label(it.name)
            }
            onUserSelect(2) { switch(it) }
        }
    }

    private fun switch(vault: Vault?) {
        if (vault == null) {
            var view = find(VaultCreate::class)
            replaceWith(view, ViewTransition.FadeThrough(0.5.seconds))
            return
        }
        val model = VaultModel(vault)
        val scope = Scope()
        setInScope(model, scope)
        var view = find(VaultShow::class, scope)
        replaceWith(view, ViewTransition.FadeThrough(0.5.seconds))
    }
}
