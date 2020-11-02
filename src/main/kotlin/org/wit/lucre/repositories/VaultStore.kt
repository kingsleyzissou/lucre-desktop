package org.wit.lucre.repositories

import org.wit.lucre.models.Vault
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

class VaultStore : CRUDStore<Vault>("vaults.json") {

    override fun deserialize() {
        var contents: JsonObject = read(filename)!!
        var arr = contents.jsonArray("list")?.toModel<Vault>()
        arr?.forEach { list[it.id] = it }
    }
}
