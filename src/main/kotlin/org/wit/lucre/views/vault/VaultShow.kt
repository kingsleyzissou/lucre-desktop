package org.wit.lucre.views.vault

import org.wit.lucre.controllers.VaultController
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.* // ktlint-disable no-wildcard-imports

class VaultShow : Fragment("Show Vault") {
    val model: VaultModel by inject()
    private val vaultController: VaultController by inject()
    private val data = vaultController.show().asObservable()

    override val root = borderpane {
        top = label(model.name)
        center = tableview(data) {
        }
    }
}
