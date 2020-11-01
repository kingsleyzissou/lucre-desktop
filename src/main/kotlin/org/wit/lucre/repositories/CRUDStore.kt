package org.wit.lucre.repositories

import mu.KotlinLogging
import org.wit.lucre.utilities.fileExists

abstract class CRUDStore<T>(var filename: String) : CRUDRepositoryInterface<T> {

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

    override fun addAll(values: List<T>) {
        values.forEach { v -> this.create(v) }
    }

    internal fun logAll() {
        list.forEach { logger.info("$it") }
    }

    abstract fun serialize()

    abstract fun deserialize()
}
