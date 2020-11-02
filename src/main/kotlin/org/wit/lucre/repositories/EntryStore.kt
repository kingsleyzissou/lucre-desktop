package org.wit.lucre.repositories

import org.wit.lucre.models.Entry
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

class EntryStore : CRUDStore<Entry>("entries.json") {

    override fun update(value: Entry) {
        var entry = find(value.id)
        if (entry != null) {
            entry.amount = value.amount
            entry.type = value.type
            entry.vendor = value.vendor
            entry.description = value.description
            entry.category = value.category
            entry.vault = value.vault
            entry.date = value.date
            serialize()
        }
    }

    override fun deserialize() {
        var contents: JsonObject = read(filename)!!
        var arr = contents.jsonArray("list")?.toModel<Entry>()
        arr?.forEach { list[it.id] = it }
    }
}
