package org.wit.lucre.views.vault

import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.*

class VaultEdit : Fragment("Edit Vault") {
    private val vaultController: VaultController by inject()
    private val model: VaultModel by inject()

    private val modelName = model.name.value

    private val currencies = listOf(
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
            text("Edit $modelName") {
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
    }

    private fun update() {
        vaultController.update(model.item)
        back()
    }

    private fun back() {
        val scope = Scope()
        setInScope(model, scope)
        var view = find(VaultShow::class, scope)
        replaceWith(view, ViewTransition.Slide(0.2.seconds))
    }
}
