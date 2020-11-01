package org.wit.lucre.views.vault

import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.Vault
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.* // ktlint-disable no-wildcard-imports

class VaultIndex : Fragment("Vault List") {
    private val vaultController: VaultController by inject()
    private val data = vaultController.index()

    override val root = datagrid(data) {
        cellCache {
            label(it.name)
        }
        onUserSelect(2) { switch(it) }
    }

    private fun switch(vault: Vault) {
        val model = VaultModel(vault)
        val scope = Scope()
        setInScope(model, scope)
        replaceWith(find<VaultShow>(scope))
    }
}
