package org.wit.lucre.controllers

import org.wit.lucre.models.Entry
import org.wit.lucre.models.Vault
import org.wit.lucre.repositories.EntryStore
import org.wit.lucre.repositories.VaultStore
import tornadofx.Controller

class VaultController : Controller() {

    private val vaults = VaultStore()
    private val entries = EntryStore()

    fun index(): List<Vault> {
        return vaults.all()
    }

    fun show(): List<Entry> {
        return entries.all()
    }

    fun create(name: String, currency: String, description: String = "") {
        vaults.create(
            Vault(
                name,
                description,
                currency
            )
        )
    }

    fun update(id: String, name: String, currency: String, description: String = "") {
        vaults.update(
            Vault(
                name,
                description,
                currency,
                id
            )
        )
    }
}
