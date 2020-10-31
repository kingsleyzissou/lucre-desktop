package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import javafx.beans.property.SimpleStringProperty
import tornadofx.* // ktlint-disable no-wildcard-imports

class Vault(
    name: String? = null,
    description: String? = null,
    currency: String? = null,
    var id: String = NanoIdUtils.randomNanoId()
) {
    val nameProperty = SimpleStringProperty(this, "name", name)
    val descProperty = SimpleStringProperty(this, "description", description)
    val currencyProperty = SimpleStringProperty(this, "currency", currency)

    var name: String by nameProperty
    var description: String by descProperty
    var currency: String by currencyProperty
}
