package org.wit.lucre.repositories

import org.wit.lucre.models.VaultModel

class VaultStore : CRUDRepositoryInterface<VaultModel> {

    private val vaults = HashMap<String, VaultModel>()

    override fun all(): List<VaultModel> {
        return vaults.values.toList()
    }

    override fun find(id: String): VaultModel? {
        return vaults[id]
    }

    override fun create(vault: VaultModel) {
        vaults[vault.id] = vault
    }

    override fun update(vault: VaultModel) {
        vaults[vault.id] = vault
    }

    override fun addAll(vaults: List<VaultModel>) {
        vaults.forEach { v -> this.create(v) }
    }
}
