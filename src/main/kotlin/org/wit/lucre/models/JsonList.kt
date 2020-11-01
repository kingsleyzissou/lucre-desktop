package org.wit.lucre.models

import tornadofx.JsonBuilder
import tornadofx.JsonModel
import tornadofx.jsonArray
import javax.json.JsonObject

class JsonList(var list: List<JsonModel>) : JsonModel {

    override fun updateModel(json: JsonObject) {
        with(json) {
            var arr = json.jsonArray("list")!!
            list = ArrayList<JsonModel>()
            arr.forEach {
                val vault = Vault()
                vault.updateModel(it as JsonObject)
                (list as ArrayList<JsonModel>).add(vault)
            }
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("list", list)
        }
    }
}
