package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import javafx.scene.paint.Color
import org.wit.lucre.utilities.toHexString
import tornadofx.JsonBuilder
import tornadofx.getProperty
import tornadofx.property
import tornadofx.string
import javax.json.JsonObject

/**
 * Category model class for the various
 * income/expense categories. A category
 * comprises of a name, description and a
 * display color
 */
class Category(
    name: String? = null,
    description: String? = null,
    color: Color? = null,
    override var id: String = NanoIdUtils.randomNanoId()
) : Model() {
    /** Properties for TornadoFX ViewModels */
    fun nameProperty() = getProperty(Category::name)
    fun descProperty() = getProperty(Category::description)
    fun colorProperty() = getProperty(Category::color)

    var name: String by property(name)
    var description: String by property(description)
    var color: Color by property(color)

    /**
     * Computed color property converted
     * to a string from a JavaFX.Color
     */
    val hexColor: String get() {
        return toHexString(this.color)!!
    }

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
            color = Color.web(string("color").toString())
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
            add("color", hexColor)
            add("id", id)
        }
    }

    /**
     * Override to display category name
     * for combobox selection
     */
    override fun toString(): String {
        return name
    }
}
