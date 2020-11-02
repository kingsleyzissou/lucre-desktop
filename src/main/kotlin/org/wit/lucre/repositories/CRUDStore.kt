package org.wit.lucre.repositories

import mu.KotlinLogging
import org.wit.lucre.models.Model
import org.wit.lucre.utilities.fileExists
import org.wit.lucre.utilities.write
import tornadofx.jsonArray
import javax.json.Json

abstract class CRUDStore<T : Model>(var filename: String) : CRUDRepositoryInterface<T> {

    private val logger = KotlinLogging.logger {}
    var list: HashMap<String, T> = HashMap()

    init {
        if (fileExists(filename)) {
            deserialize()
        }
    }

    override fun all(): List<T> {
        return list.values.toList()
    }

    override fun find(id: String): T? {
        return list[id]
    }

    override fun create(value: T) {
        list[value.id] = value
        serialize()
    }

    override fun addAll(values: List<T>) {
        values.forEach { v -> this.create(v) }
    }

    internal fun logAll() {
        list.forEach { logger.info("$it") }
    }

    fun serialize() {
        var jsonObject = Json.createObjectBuilder()
        var jsonArray = Json.createArrayBuilder()
        all().map { jsonArray.add(it.toJSON()) }
        jsonObject.add("list", jsonArray)
        write(filename, jsonObject.build().toString())
    }

    abstract fun deserialize()
}
