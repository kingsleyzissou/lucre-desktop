package org.wit.lucre.repositories

import org.wit.lucre.models.Vault
import org.wit.lucre.utilities.read
import org.wit.lucre.utilities.write
import tornadofx.jsonArray
import javax.json.Json
import javax.json.JsonObject

class VaultStore : CRUDStore<Vault>(
    "vaults.json"
) {

    override fun create(value: Vault) {
        list[value.id] = value
        serialize()
    }

    override fun update(value: Vault) {
        val vault = find(value.id)
        if (vault != null) {
            vault.name = value.name
            vault.description = value.description
            vault.currency = value.currency
            serialize()
        }
    }

    override fun serialize() {
        var jsonObject = Json.createObjectBuilder()
        var jsonArray = Json.createArrayBuilder()
        all().map { jsonArray.add(it.toJSON()) }
        jsonObject.add("list", jsonArray)
        write(filename, jsonObject.build().toString())
    }

    override fun deserialize() {
        var contents: JsonObject = read(filename)!!
        var arr = contents.jsonArray("list")!!
        arr.forEach {
            val vault = Vault()
            vault.updateModel(it as JsonObject)
            list[vault.id] = vault
        }
    }
}
