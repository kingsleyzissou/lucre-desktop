package org.wit.lucre.controllers

import org.wit.lucre.models.Entry
import org.wit.lucre.repositories.EntryStore
import tornadofx.Controller

class EntryController : Controller() {

    private val entries = EntryStore()

    fun index(): List<Entry> {
        return entries.all()
    }

}