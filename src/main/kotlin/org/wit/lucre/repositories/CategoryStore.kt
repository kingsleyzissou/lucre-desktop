package org.wit.lucre.repositories

import org.wit.lucre.models.Category
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

class CategoryStore : CRUDStore<Category>("categories.json") {

    override fun deserialize() {
        var contents: JsonObject = read(filename)!!
        var arr = contents.jsonArray("list")?.toModel<Category>()
        arr?.forEach { list[it.id] = it }
    }
}
