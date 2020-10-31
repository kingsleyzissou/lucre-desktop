package org.wit.lucre.views.vault

import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.VaultModel
import tornadofx.Fragment
import tornadofx.asObservable
import tornadofx.borderpane
import tornadofx.label
import tornadofx.tableview

class VaultShow : Fragment("Show Vault") {
    val vault: VaultModel by param()
    private val vaultController: VaultController by inject()
    private val data = vaultController.show().asObservable()

    override val root = borderpane {
        top = label(vault.name)
        center = tableview(data) {
        }
    }
}
