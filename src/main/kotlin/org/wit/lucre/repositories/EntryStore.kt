package org.wit.lucre.repositories

import org.wit.lucre.models.Entry

class EntryStore : CRUDRepositoryInterface<Entry> {

    private val entries = HashMap<String, Entry>()

    override fun all(): List<Entry> {
        return entries.values.toList()
    }

    override fun find(id: String): Entry? {
        return entries[id]
    }

    override fun create(value: Entry) {
        entries[value.id] = value
    }

    override fun update(value: Entry) {
        entries[value.id] = value
    }

    override fun addAll(values: List<Entry>) {
        values.forEach { e -> this.create(e) }
    }
}
