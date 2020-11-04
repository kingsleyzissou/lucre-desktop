package org.wit.lucre.repositories

import org.wit.lucre.models.Entry
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import java.util.function.Predicate
import javax.json.JsonObject

class EntryStore(file: String = "entries.json") : CRUDStore<Entry>(file) {

    fun where(predicate: Predicate<Entry>): List<Entry> {
        return list
            .filter { predicate.test(it.value) }
            .values
            .toList()
    }

    override fun deserialize() {
        val contents: JsonObject = read(filename)!!
        val arr = contents.jsonArray("list")?.toModel<Entry>()
        arr?.forEach { list[it.id] = it }
    }
}
