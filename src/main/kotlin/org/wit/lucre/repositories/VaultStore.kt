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

    override fun create(value: VaultModel) {
        vaults[value.id] = value
    }

    override fun update(value: VaultModel) {
        vaults[value.id] = value
    }

    override fun addAll(values: List<VaultModel>) {
        values.forEach { v -> this.create(v) }
    }
}
