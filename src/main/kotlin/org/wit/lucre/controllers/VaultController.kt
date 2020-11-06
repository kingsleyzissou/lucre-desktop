package org.wit.lucre.controllers

import org.wit.lucre.models.Vault
import org.wit.lucre.repositories.VaultStore
import tornadofx.Controller

class VaultController : Controller() {

    private val store = VaultStore()

    fun index(): List<Vault> {
        return store.all()
    }

    fun find(id: String): Vault {
        return store.find(id)!!
    }

    fun create(vault: Vault) {
        store.create(vault)
    }

    fun update(vault: Vault) {
        store.update(vault)
    }

    fun delete(id: String) {
        store.delete(id)
    }
}
