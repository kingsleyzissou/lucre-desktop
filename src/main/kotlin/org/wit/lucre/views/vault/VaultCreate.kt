package org.wit.lucre.views.vault

import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import tornadofx.Fragment
import tornadofx.action
import tornadofx.button
import tornadofx.label
import tornadofx.textfield
import tornadofx.hbox
import tornadofx.singleAssign
import tornadofx.vbox
import tornadofx.combobox

class VaultCreate : Fragment("Create Vault") {

    private var nameField: TextField by singleAssign()
    private var descriptionField: TextField by singleAssign()
    private var currencyField: ComboBox<String> by singleAssign()

    override val root = vbox {
        hbox {
            label("Vault name: ")
            nameField = textfield()
        }
        hbox {
            label("Description: ")
            descriptionField = textfield()
        }
        hbox {
            label("Currency: ")
            currencyField = combobox<String>() {
                items = FXCollections.observableArrayList(
                    "$", "£", "€", "AED", "R", "R$", "¥"
                )
            }
        }
        hbox {
            button("Clear") {
                action { clear() }
            }
            button("Create") {
                action { create() }
            }
        }
    }

    private fun clear() {
        nameField.clear()
        descriptionField.clear()
        currencyField.selectionModel.clearSelection()
    }

    private fun create() {

    }
}
