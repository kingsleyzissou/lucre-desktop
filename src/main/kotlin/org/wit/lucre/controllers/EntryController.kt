package org.wit.lucre.controllers

import org.wit.lucre.models.Entry
import org.wit.lucre.repositories.EntryStore
import tornadofx.Controller

class EntryController : Controller() {

    private val store = EntryStore()

    fun index(): List<Entry> {
        return store.all()
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
