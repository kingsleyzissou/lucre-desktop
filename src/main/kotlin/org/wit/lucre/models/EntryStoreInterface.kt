package org.wit.lucre.models

interface EntryStoreInterface {
    fun all(): List<EntryModel>
    fun find(id: String): EntryModel?
    fun create(entry: EntryModel)
    fun update(entry: EntryModel)
}
