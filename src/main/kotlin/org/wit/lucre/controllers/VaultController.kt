package org.wit.lucre.controllers

import org.wit.lucre.models.Category
import org.wit.lucre.models.Entry
import org.wit.lucre.models.Vault
import org.wit.lucre.repositories.EntryStore
import org.wit.lucre.repositories.VaultStore
import tornadofx.Controller

class VaultController : Controller() {

    private val store = VaultStore()

    var category = Category(
        "Takeout",
        "Dine-in and takeaways",
        "#ff0000"
    )

    fun index(): List<Vault> {
        return store.all()
    }

    fun create(vault: Vault) {
        store.create(vault)
    }

    fun update(vault: Vault) {
        store.update(vault)
    }
}
