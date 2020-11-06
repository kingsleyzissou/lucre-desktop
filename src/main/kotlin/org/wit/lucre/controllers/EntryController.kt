package org.wit.lucre.controllers

import org.wit.lucre.models.Category
import org.wit.lucre.models.Entry
import org.wit.lucre.repositories.EntryStore
import org.wit.lucre.services.IncomeService
import tornadofx.Controller
import java.util.function.Predicate

class EntryController : Controller() {

    private val store = EntryStore()
    private val service = IncomeService()

    fun index(): List<Entry> {
        return store.all()
    }

    fun filter(predicate: Predicate<Entry>): List<Entry> {
        return store.where(predicate).sortedByDescending { it.date }
    }

    fun chartData(entries: List<Entry>): Map<Category, Double> {
        if (entries.isEmpty()) return emptyMap()
        return service.expenseCategories(entries)
    }

    fun find(id: String): Entry {
        return store.find(id)!!
    }

    fun create(entry: Entry) {
        store.create(entry)
    }

    fun update(entry: Entry) {
        store.update(entry)
    }

    fun delete(id: String) {
        store.delete(id)
    }

    fun balance(entries: List<Entry>): Float {
        return service.balance(entries)
    }
}
