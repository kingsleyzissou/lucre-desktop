package org.wit.lucre.models

class EntryMemoryStore : EntryStoreInterface {

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
}
