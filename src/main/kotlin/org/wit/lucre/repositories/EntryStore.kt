package org.wit.lucre.repositories

import org.wit.lucre.models.EntryModel

class EntryStore : CRUDRepositoryInterface<EntryModel> {

    private val entries = HashMap<String, EntryModel>()

    override fun all(): List<EntryModel> {
        return entries.values.toList()
    }

    override fun find(id: String): EntryModel? {
        return entries[id]
    }

    override fun create(value: EntryModel) {
        entries[value.id] = value
    }

    override fun update(value: EntryModel) {
        entries[value.id] = value
    }

    override fun addAll(values: List<EntryModel>) {
        values.forEach { e -> this.create(e) }
    }
}
