package org.wit.lucre.views.entry

import javafx.beans.property.Property
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.wit.lucre.models.Entry
import org.wit.lucre.models.EntryType
import org.wit.lucre.viewmodels.EntryModel
import org.wit.lucre.viewmodels.VaultModel
import tornadofx.*

class EntryCreate : Fragment("Create Entry") {
    private val parent: VaultModel by inject()
    val model: EntryModel = EntryModel(Entry())

    private val vendor = model.bind { SimpleStringProperty() }
    private val type = model.bind { SimpleObjectProperty<Enum<EntryType>>() }
    private val description = model.bind { SimpleStringProperty() }
    private val amount = model.bind { SimpleFloatProperty() }
    private val category = model.bind { SimpleStringProperty() }
    private val vault = model.bind { SimpleStringProperty(parent.item.id) }

    private val types = listOf<Enum<EntryType>>(
        EntryType.EXPENSE, EntryType.INCOME
    )

    override val root = vbox {
        form {
            fieldset {
                field("Shop:") {
                    textfield(vendor).required()
                }
                field("Description:") {
                    textfield(description)
                }
                field("Amount:") {
                    textfield(amount).required()
                }
                field("Expense Type:") {
                    combobox(type, types).required()
                    useMaxSize = true
                }
                field("Category:") {
                    TODO("Add list cateogries")
                    textfield(category)
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
        println(type)
    }
}
