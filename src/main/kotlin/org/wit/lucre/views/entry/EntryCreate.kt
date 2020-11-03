package org.wit.lucre.views.entry

import javafx.beans.property.Property
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.wit.lucre.controllers.CategoryController
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.models.Category
import org.wit.lucre.models.Entry
import org.wit.lucre.models.EntryType
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import org.wit.lucre.views.vault.VaultShow
import tornadofx.* // ktlint-disable no-wildcard-imports

class EntryCreate : Fragment("Create Entry") {
    private val vault: VaultModel by inject()
    private val entryController: EntryController by inject()
    private val categoryController: CategoryController by inject()

    val model: EntryModel = EntryModel(Entry())

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
        entryController.create(
            model.amount.value.toFloat(),
            model.type.value,
            model.vendor.value,
            model.category.value,
            vault.item.id,
            model.description.value
        )
        back()
    }

    private fun back() {
        val scope = Scope()
        setInScope(vault, scope)
        replaceWith(find<VaultShow>(scope))
    }
}

