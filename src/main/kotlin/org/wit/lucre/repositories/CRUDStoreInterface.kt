package org.wit.lucre.repositories

interface CRUDStoreInterface<T> {
    fun all(): List<T>
    fun find(id: String): T?
    fun create(value: T)
    fun update(value: T)
    fun delete(id: String)
    fun addAll(values: List<T>)
}
