package org.wit.lucre.repositories

import org.wit.lucre.models.Vault
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

class VaultStore(file: String = "vaults.json") : CRUDStore<Vault>(file) {

    override fun deserialize() {
        val contents: JsonObject = read(filename)!!
        val arr = contents.jsonArray("list")?.toModel<Vault>()
        arr?.forEach { list[it.id] = it }
    }
}
