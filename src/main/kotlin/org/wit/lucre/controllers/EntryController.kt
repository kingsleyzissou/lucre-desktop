package org.wit.lucre.controllers

import org.wit.lucre.models.Entry
import org.wit.lucre.repositories.EntryStore
import tornadofx.Controller
import java.util.function.Predicate

class EntryController : Controller() {

    private val store = EntryStore()

    fun index(): List<Entry> {
        return store.all()
    }

    fun filter(predicate: Predicate<Entry>): List<Entry> {
        return store.where(predicate)
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
}
