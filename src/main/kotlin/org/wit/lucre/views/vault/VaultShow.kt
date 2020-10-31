package org.wit.lucre.views.vault

import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.Vault
import tornadofx.* // ktlint-disable no-wildcard-imports

class VaultShow : Fragment("Show Vault") {
    val vault: Vault by param()
    private val vaultController: VaultController by inject()
    private val data = vaultController.show().asObservable()

    override val root = borderpane {
        top = label(vault.name)
        center = tableview(data) {
        }
    }
}
