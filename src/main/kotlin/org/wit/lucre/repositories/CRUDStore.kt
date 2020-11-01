package org.wit.lucre.repositories

import mu.KotlinLogging
import org.wit.lucre.utilities.fileExists

abstract class CRUDStore<JsonModel>(var filename: String) : CRUDRepositoryInterface<JsonModel> {

    private val logger = KotlinLogging.logger {}
    var list: HashMap<String, JsonModel> = HashMap()

    init {
        if (fileExists(filename)) {
            deserialize()
        }
    }

    abstract fun serialize(value: JsonModel)

    abstract fun deserialize()

    internal fun logAll() {
        list.forEach { logger.info("$it") }
    }
}
