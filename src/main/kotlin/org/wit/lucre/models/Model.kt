package org.wit.lucre.models

import tornadofx.JsonModel

/**
 * Abstract class that is used
 * as the base class for all the models.
 *
 * This is to facilitate a more generic CRUDStore,
 * by ensuring that each store has an id element.
 */
abstract class Model : JsonModel {
    abstract var id: String
}
