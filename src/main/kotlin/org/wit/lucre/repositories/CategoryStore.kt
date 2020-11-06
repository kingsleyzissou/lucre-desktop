package org.wit.lucre.repositories

import org.wit.lucre.models.Category
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

/**
 * CategoryStore for storing and retrieving
 * category items. The store is saved to a
 * json file.
 */
class CategoryStore(file: String = "categories.json") : CRUDStore<Category>(file) {

    /**
     * Custom deserialize method for the
     * category store to convert JSONObject
     * read from file into a list of category items
     */
    override fun deserialize() {
        // get the file contents
        val contents: JsonObject = read(filename)!!
        // convert the file contents to a model using `TornadoFX.toModel` helper
        val arr = contents.jsonArray("list")?.toModel<Category>()
        // push the item to the CRUDStore list
        arr?.forEach { list[it.id] = it }
    }
}
