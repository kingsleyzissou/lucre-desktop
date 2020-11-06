package org.wit.lucre.views.entry

import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.utilities.toHexString
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.category.CategoryEdit
import org.wit.lucre.views.category.CategoryIndex
import org.wit.lucre.views.vault.VaultShow
import tornadofx.*

class EntryDelete : Fragment("Delete Entry") {
    private val model: EntryModel by inject()
    private val entryController: EntryController by inject()
    private val vaultController: VaultController by inject()

    val vault = vaultController.find(model.vault.value)

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Back").action { back() }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text("Delete entry") {
                font = Font(20.0)
            }
        }
        center = form {
            fieldset {
                field("Vendor:") {
                    label(model.vendor.value)
                }
                field("Description:") {
                    label(model.description.value)
                }
                field("Type:") {
                    label(model.type.value.toString())
                }
                field("Amount:") {
                    label {
                        text = "${vault.currency}${model.amount.value}"
                    }
                }
                field {
                    button("Back").action { back() }
                    button("Delete") {
                        action {
                            confirm(
                                "Delete entry",
                                "Are you sure?",
                                actionFn = { delete() }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun delete() {
        entryController.delete(model.item.id)
        back()
    }

    private fun back() {
        val scope = Scope()
        setInScope(VaultModel(vault), scope)
        var view = find(VaultShow::class, scope)
        replaceWith(view, ViewTransition.FadeThrough(0.4.seconds))
    }
}
