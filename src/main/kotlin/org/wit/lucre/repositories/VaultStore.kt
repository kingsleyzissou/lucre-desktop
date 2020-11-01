package org.wit.lucre.repositories

import org.wit.lucre.models.Vault

class VaultStore : CRUDRepositoryInterface<Vault> {

    private val vaults = HashMap<String, Vault>()

    override fun all(): List<Vault> {
        return vaults.values.toList()
    }

    override fun find(id: String): Vault? {
        return vaults[id]
    }

    override fun create(value: Vault) {
        vaults[value.id] = value
    }

    override fun update(value: Vault) {
        vaults[value.id] = value
    }

    override fun addAll(values: List<Vault>) {
        values.forEach { v -> this.create(v) }
    }
}
