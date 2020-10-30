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

    override fun create(entry: EntryModel) {
        entries[entry.id] = entry
    }

    override fun update(entry: EntryModel) {
        entries[entry.id] = entry
    }

    override fun addAll(entries: List<EntryModel>) {
        entries.forEach { e -> this.create(e) }
    }
}
