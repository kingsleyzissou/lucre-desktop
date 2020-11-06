package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import javafx.scene.paint.Color
import org.wit.lucre.utilities.toHexString
import tornadofx.JsonBuilder
import tornadofx.getProperty
import tornadofx.property
import tornadofx.string
import javax.json.JsonObject
import kotlin.reflect.typeOf

class Category(
    name: String? = null,
    description: String? = null,
    color: Color? = null,
    override var id: String = NanoIdUtils.randomNanoId()
) : Model() {
    fun nameProperty() = getProperty(Category::name)
    fun descProperty() = getProperty(Category::description)
    fun colorProperty() = getProperty(Category::color)

    var name: String by property(name)
    var description: String by property(description)
    var color: Color by property(color)

    val hexColor: String get() {
        return toHexString(this.color)!!
    }

    override fun updateModel(json: JsonObject) {
        with(json) {
            name = string("name").toString()
            description = string("description").toString()
            color = Color.web(string("color").toString())
            id = string("id").toString()
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("name", name)
            add("description", description)
            add("color", hexColor)
            add("id", id)
        }
    }

    override fun toString(): String {
        return name
    }
}
