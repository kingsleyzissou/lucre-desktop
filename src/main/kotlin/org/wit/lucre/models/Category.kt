package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import tornadofx.getProperty
import tornadofx.property

class Category(
    name: String,
    description: String,
    color: String,
    var id: String = NanoIdUtils.randomNanoId()
) {
    fun nameProperty() = getProperty(Category::name)
    fun descProperty() = getProperty(Category::description)
    fun colorProperty() = getProperty(Category::color)

    var name: String by property(name)
    var description: String by property(description)
    var color: String by property(color)

}
