package org.wit.lucre.views.vault

import javafx.beans.property.SimpleStringProperty
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.* // ktlint-disable no-wildcard-imports

class VaultEdit : Fragment("Edit Vault") {
    private val vaultController: VaultController by inject()
    private val model: VaultModel by inject()

    private val currencies = listOf(
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
                    button("Clear").action {
                        model.rollback()
                    }
                    button("Update") {
                        enableWhen(model.valid and model.dirty)
                        isDefaultButton = true
                        action {
                            model.commit()
                            update()
                        }
                    }
                }
            }
        }
    }

    private fun update() {
        vaultController.update(model.item)
        replaceWith(VaultIndex::class)
    }
}
