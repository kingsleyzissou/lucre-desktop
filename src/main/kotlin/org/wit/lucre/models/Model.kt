package org.wit.lucre.models

import tornadofx.JsonModel

abstract class Model : JsonModel {
    abstract var id: String
}
