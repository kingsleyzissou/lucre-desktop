package org.wit.lucre.views.vault

import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.VaultModel
import tornadofx.Fragment
import tornadofx.datagrid
import tornadofx.label

class VaultIndex : Fragment("Vault List") {
    val vaultController: VaultController by inject()
    val data = vaultController.index()

    override val root = datagrid(data) {
        cellCache {
            label(it.name)
        }
        onUserSelect(2) { switch(it) }
    }

    private fun switch(vault: VaultModel) {
        replaceWith(find<VaultShow>(mapOf(VaultShow::vault to vault)))
    }
}
