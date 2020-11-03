package org.wit.lucre.views.vault

import javafx.beans.property.SimpleStringProperty
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.Vault
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.* // ktlint-disable no-wildcard-imports

class VaultCreate : Fragment("Create Vault") {

    private val vaultController: VaultController by inject()
    private var model = VaultModel(Vault())

    private val currencies = listOf<String>(
        "$", "£", "€", "AED", "R", "R$", "¥"
    )

    override val root = vbox {
        form {
            fieldset {
                field("Vault name:") {
                    textfield(model.name).required()
                }
                field("Description:") {
                    textfield(model.description)
                }
                field("Currency:") {
                    combobox<String>(model.currency, currencies).required()
                    useMaxSize = true
                }
                field {
                    button("Clear") {
                        action { model.rollback() }
                        isCancelButton = true
                    }
                    button("Create") {
                        enableWhen(model.valid)
                        isDefaultButton = true
                        action {
                            model.commit()
                            create()
                        }
                    }
                }
            }
        }
    }

    private fun create() {
        vaultController.create(model.item)
        replaceWith(VaultIndex::class)
    }
}
