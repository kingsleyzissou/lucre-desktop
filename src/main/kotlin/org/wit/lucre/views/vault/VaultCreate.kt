package org.wit.lucre.views.vault

import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.Vault
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.*

class VaultCreate : Fragment("Create Vault") {
    private val vaultController: VaultController by inject()
    private var model = VaultModel(Vault())

    private val currencies = listOf<String>(
        "$", "£", "€", "AED", "R", "R$", "¥"
    )

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Back").action { back() }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text("Create vault:") {
                font = Font(14.0)
            }
        }
        center = vbox {
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
    }

    private fun create() {
        vaultController.create(model.item)
        back()
    }

    private fun back() {
        var view = find(VaultIndex::class)
        replaceWith(view, ViewTransition.FadeThrough(0.4.seconds))
    }
}
