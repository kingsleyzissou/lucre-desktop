package org.wit.lucre.repositories

import org.wit.lucre.models.Vault
import org.wit.lucre.utilities.read
import tornadofx.jsonArray
import tornadofx.toModel
import javax.json.JsonObject

class VaultStore : CRUDStore<Vault>("vaults.json") {

    override fun update(value: Vault) {
        val vault = find(value.id)
        if (vault != null) {
            vault.name = value.name
            vault.description = value.description
            vault.currency = value.currency
            serialize()
        }
    }

    override fun deserialize() {
        var contents: JsonObject = read(filename)!!
        var arr = contents.jsonArray("list")?.toModel<Vault>()
        arr?.forEach { list[it.id] = it }
    }
}
