package org.wit.lucre.views.entry

import javafx.beans.property.Property
import org.wit.lucre.controllers.CategoryController
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.models.EntryType
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.vault.VaultShow
import tornadofx.*

class EntryEdit : Fragment("Edit Entry") {
    private val model: EntryModel by inject()
    private val vault: VaultModel by inject()
    private val entryController: EntryController by inject()
    private val categoryController: CategoryController by inject()

    private val types = listOf<Enum<EntryType>>(
        EntryType.EXPENSE, EntryType.INCOME
    )

    override val root = vbox {
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

    private fun update() {
        entryController.update(model.item)
        back()
    }

    private fun back() {
        val scope = Scope()
        setInScope(vault, scope)
        replaceWith(find<VaultShow>(scope))
    }
}
