package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

/**
 * Vault model class for the accounts or vaults in the app.
 * A vault comprises of a name, description and a
 * currency
 */
class Vault(
    name: String? = null,
    description: String? = null,
    currency: String? = null,
    override var id: String = NanoIdUtils.randomNanoId()
) : Model() {
    /** Properties for TornadoFX ViewModels */
    val nameProperty = SimpleStringProperty(this, "name", name)
    val descProperty = SimpleStringProperty(this, "description", description)
    val currencyProperty = SimpleStringProperty(this, "currency", currency)

    var name: String by nameProperty
    var description: String by descProperty
    var currency: String by currencyProperty

    /**
     * JsonModel class for deserializing JSON fields
     * back to the Category model. Each field has a custom action to convert the
     * JSON object field back to the desired type. This method overrides
     * the JSONModel TornadoFX `updateModel` function
     */
    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name").toString()
            description = string("description").toString()
            currency = string("currency").toString()
            id = string("id").toString()
        }
    }

    /**
     * Method for serializing category fields
     * down to JSON strings. This method overrides
     * the JSONModel TornadoFX `toJSON` function
     */
    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("name", name)
            add("description", description)
            add("currency", currency)
            add("id", id)
        }
    }
}
