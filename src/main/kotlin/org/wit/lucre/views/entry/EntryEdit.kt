package org.wit.lucre.views.entry

import javafx.beans.property.Property
import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.controllers.CategoryController
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.controllers.VaultController
import org.wit.lucre.models.EntryType
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.vault.VaultShow
import tornadofx.*

class EntryEdit : Fragment("Edit Entry") {
    private val model: EntryModel by inject()
    private val vaultController: VaultController by inject()
    private val entryController: EntryController by inject()
    private val categoryController: CategoryController by inject()

    private val types = listOf<Enum<EntryType>>(
        EntryType.EXPENSE, EntryType.INCOME
    )

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            gridpane() {
                button("Back").action { back() }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text("Edit entry:") {
                font = Font(14.0)
            }
        }
        center = vbox {
            form {
                fieldset {
                    field("Shop:") {
                        textfield(model.vendor).required()
                    }
                    field("Description:") {
                        textfield(model.description)
                    }
                    field("Amount:") {
                        textfield(model.amount as Property<Number>).required()
                    }
                    field("Expense Type:") {
                        combobox(model.type, types).required()
                        useMaxSize = true
                    }
                    field("Category:") {
                        combobox(model.category, categoryController.index()).required()
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
        entryController.update(model.item)
        back()
    }

    private fun back() {
        val scope = Scope()
        val vault = vaultController.find(model.vault.value)
        setInScope(VaultModel(vault), scope)
        var view = find(VaultShow::class, scope)
        replaceWith(view, ViewTransition.FadeThrough(0.4.seconds))
    }
}
