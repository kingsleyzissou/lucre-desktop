package org.wit.lucre.repositories

import org.wit.lucre.models.JsonList
import org.wit.lucre.models.Vault
import org.wit.lucre.utilities.read
import tornadofx.JsonModel
import tornadofx.save
import java.io.FileOutputStream
import java.io.StringReader
import javax.json.Json
import javax.json.JsonObject
import javax.json.JsonReader

class VaultStore : CRUDStore<Vault>("vaults.json") {

    override fun all(): List<Vault> {
        return list.values.toList()
    }

    override fun find(id: String): Vault? {
        return list[id]
    }

    override fun create(value: Vault) {
        list[value.id] = value
        serialize(value)
    }

    override fun update(value: Vault) {
        val vault = find(value.id)
        if (vault != null) {
            vault.name = value.name
            vault.description = value.description
            vault.currency = value.currency
        }
        serialize(value)
    }

    override fun addAll(values: List<Vault>) {
        values.forEach { v -> this.create(v) }
    }

    override fun serialize(value: Vault) {
        val output = FileOutputStream("vaults.json")
        val arrList: List<JsonModel> = list.values.map { vault: Vault -> vault }
        val jsonList = JsonList(arrList)
        jsonList.save(output)
    }

    override fun deserialize() {
        val file = read(filename)
        val jsonReader: JsonReader = Json.createReader(StringReader(file))
        val jsonArr: JsonObject = jsonReader.readObject()
        jsonReader.close()
        val jsonList = JsonList(ArrayList())
        jsonList.updateModel(jsonArr)
        for (it: JsonModel in jsonList.list) {
            val vault: Vault = it as Vault
            list.put(vault.id, vault)
        }
    }
}
