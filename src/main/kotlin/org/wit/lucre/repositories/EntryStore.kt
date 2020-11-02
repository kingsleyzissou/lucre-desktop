package org.wit.lucre.repositories

import org.wit.lucre.models.Entry
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

class EntryStore : CRUDStore<Entry>("entries.json") {

    override fun deserialize() {
        var contents: JsonObject = read(filename)!!
        var arr = contents.jsonArray("list")?.toModel<Entry>()
        arr?.forEach { list[it.id] = it }
    }
}
