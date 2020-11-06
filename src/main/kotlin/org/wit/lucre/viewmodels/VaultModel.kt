package org.wit.lucre.viewmodels

import org.wit.lucre.models.Vault
import tornadofx.ItemViewModel

/**
 * TornadoFX ViewModel for corresponding Vault model
 */
class VaultModel(vault: Vault) : ItemViewModel<Vault>(vault) {
    val name = bind(Vault::nameProperty)
    val description = bind(Vault::descProperty)
    val currency = bind(Vault::currencyProperty)
}
