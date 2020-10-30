package org.wit.lucre.repositories

import org.wit.lucre.models.EntryModel

interface CRUDRepositoryInterface {
    fun all(): List<EntryModel>
    fun find(id: String): EntryModel?
    fun create(entry: EntryModel)
    fun update(entry: EntryModel)
    fun addAll(entries: List<EntryModel>)
}
