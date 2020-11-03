package org.wit.lucre.repositories

import org.wit.lucre.models.Entry
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

class EntryStore(file: String = "entries.json") : CRUDStore<Entry>(file) {

    override fun deserialize() {
        val contents: JsonObject = read(filename)!!
        val arr = contents.jsonArray("list")?.toModel<Entry>()
        arr?.forEach { list[it.id] = it }
    }
}
