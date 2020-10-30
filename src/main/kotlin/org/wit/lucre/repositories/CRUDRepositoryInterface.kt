package org.wit.lucre.repositories

import org.wit.lucre.models.EntryModel

interface CRUDRepositoryInterface<T> {
    fun all(): List<T>
    fun find(id: String): T?
    fun create(value: T)
    fun update(value: T)
    fun addAll(values: List<T>)
}
