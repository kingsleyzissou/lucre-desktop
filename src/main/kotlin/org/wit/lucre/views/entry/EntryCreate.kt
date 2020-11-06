package org.wit.lucre.views.entry

import javafx.beans.property.Property
import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.controllers.CategoryController
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.models.Entry
import org.wit.lucre.models.EntryType
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.vault.VaultShow
import tornadofx.*

class EntryCreate : Fragment("Create Entry") {
    private val vault: VaultModel by inject()
    private val entryController: EntryController by inject()
    private val categoryController: CategoryController by inject()

    val model: EntryModel = EntryModel(Entry())

    private val types = listOf<Enum<EntryType>>(
        EntryType.EXPENSE, EntryType.INCOME
    )

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Back").action { back() }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text("Create entry:") {
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
                    field("Date:") {
                        datepicker(model.date).required()
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
        model.item.vault = vault.item.id
        entryController.create(model.item)
        back()
    }

    private fun back() {
        val scope = Scope()
        setInScope(vault, scope)
        var view = find(VaultShow::class, scope)
        replaceWith(view, ViewTransition.FadeThrough(0.4.seconds))
    }
}
