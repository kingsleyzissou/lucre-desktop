package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import javafx.beans.property.SimpleStringProperty
import tornadofx.* // ktlint-disable no-wildcard-imports
import javax.json.JsonObject

class Vault(
    name: String? = null,
    description: String? = null,
    currency: String? = null,
    override var id: String = NanoIdUtils.randomNanoId()
) : Model() {
    val nameProperty = SimpleStringProperty(this, "name", name)
    val descProperty = SimpleStringProperty(this, "description", description)
    val currencyProperty = SimpleStringProperty(this, "currency", currency)

    var name: String by nameProperty
    var description: String by descProperty
    var currency: String by currencyProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name").toString()
            description = string("description").toString()
            currency = string("currency").toString()
            id = string("id").toString()
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("name", name)
            add("description", description)
            add("currency", currency)
            add("id", id)
        }
    }
}
