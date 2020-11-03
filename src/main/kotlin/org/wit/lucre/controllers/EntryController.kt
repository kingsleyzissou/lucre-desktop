package org.wit.lucre.controllers

import org.wit.lucre.models.Category
import org.wit.lucre.models.Entry
import org.wit.lucre.models.EntryType
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

    fun create(
        amount: Float,
        type: Enum<EntryType>,
        vendor: String,
        category: Category,
        vault: String,
        description: String = ""
    ) {
        store.create(
            Entry(
                amount,
                type,
                vendor,
                description,
                category,
                vault
            )
        )
    }

    fun update(entry: Entry) {
        store.update(entry)
    }
}
