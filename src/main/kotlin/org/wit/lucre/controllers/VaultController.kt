package org.wit.lucre.controllers

import javafx.scene.paint.Color
import org.wit.lucre.models.Category
import org.wit.lucre.models.Vault
import org.wit.lucre.repositories.VaultStore
import tornadofx.Controller

class VaultController : Controller() {

    private val store = VaultStore()

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
