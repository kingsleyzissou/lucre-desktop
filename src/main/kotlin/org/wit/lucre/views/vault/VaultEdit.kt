package org.wit.lucre.views.vault

import javafx.beans.property.SimpleStringProperty
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.* // ktlint-disable no-wildcard-imports

class VaultEdit : Fragment("Edit Vault") {

    private val vaultController: VaultController by inject()
    private val model: VaultModel by inject()

    private val name = model.bind{ SimpleStringProperty() }
    private val description = model.bind { SimpleStringProperty() }
    private val currency = model.bind{ SimpleStringProperty() }

    private val currencies = listOf<String>(
        "$", "£", "€", "AED", "R", "R$", "¥"
    )

    override val root = vbox {
        form {
            println(model.name.value)
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
                        enableWhen(model.valid)
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

    }
}
