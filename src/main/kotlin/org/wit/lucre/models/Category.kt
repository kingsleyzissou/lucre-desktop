package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import tornadofx.JsonBuilder
import tornadofx.getProperty
import tornadofx.property
import tornadofx.string
import javax.json.JsonObject

class Category(
    name: String? = null,
    description: String? = null,
    color: String? = null,
    override var id: String = NanoIdUtils.randomNanoId()
) : Model() {
    fun nameProperty() = getProperty(Category::name)
    fun descProperty() = getProperty(Category::description)
    fun colorProperty() = getProperty(Category::color)

    var name: String by property(name)
    var description: String by property(description)
    var color: String by property(color)

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name").toString()
            description = string("description").toString()
            color = string("color").toString()
            id = string("id").toString()
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("name", name)
            add("description", description)
            add("color", color)
            add("id", id)
        }
    }
}
