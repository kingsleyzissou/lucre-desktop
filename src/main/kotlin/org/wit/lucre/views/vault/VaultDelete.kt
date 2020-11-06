package org.wit.lucre.views.vault

import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.*

class VaultDelete : Fragment("My View") {
    private val vaultController: VaultController by inject()
    private val model: VaultModel by inject()

    private val modelName = model.name.value

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Back").action { back() }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text("Delete $modelName") {
                font = Font(14.0)
            }
        }
        center = vbox {
            form {
                fieldset {
                    field("Vault name:") {
                        label(model.item.name)
                    }
                    field("Description:") {
                        label(model.item.description)
                    }
                    field("Currency:") {
                        label(model.item.currency)
                    }
                    field {
                        button("Back").action { back() }
                        button("Delete") {
                            action {
                                confirm(
                                    "Delete vault",
                                    "Are you sure?",
                                    actionFn = { delete() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun delete() {
        vaultController.delete(model.item.id)
        var view = find(VaultIndex::class)
        replaceWith(view, ViewTransition.Slide(0.2.seconds))
    }

    private fun back() {
        val scope = Scope()
        setInScope(model, scope)
        var view = find(VaultShow::class, scope)
        replaceWith(view, ViewTransition.Slide(0.2.seconds))
    }
}
