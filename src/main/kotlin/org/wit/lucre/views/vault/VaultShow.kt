package org.wit.lucre.views.vault

import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.VaultModel
import tornadofx.*

class VaultShow : Fragment("Show Vault") {
    val vault: VaultModel by param()
    private val vaultController: VaultController by inject()
    private val data = vaultController.show(vault.id).observable()

    override val root = borderpane {
        top = label(vault.name)
        center = tableview(data) {

        }
    }
}
