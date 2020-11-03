package org.wit.lucre.repositories

import org.wit.lucre.models.Category
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

class CategoryStore(file: String = "categories.json") : CRUDStore<Category>(file) {

    override fun deserialize() {
        val contents: JsonObject = read(filename)!!
        val arr = contents.jsonArray("list")?.toModel<Category>()
        arr?.forEach { list[it.id] = it }
    }
}
