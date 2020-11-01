package org.wit.lucre.controllers

import mu.KotlinLogging
import org.wit.lucre.models.EntryModel
import org.wit.lucre.models.Vault
import org.wit.lucre.repositories.EntryStore
import org.wit.lucre.repositories.VaultStore
import tornadofx.Controller

class VaultController : Controller() {

    private val vaults = VaultStore()
    private val entries = EntryStore()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Vault controller loading " }

        vaults.addAll(
            listOf(
                Vault(
                    "AIB",
                    "Euro",
                    "€"
                ),
                Vault(
                    "HSBC",
                    "Pound",
                    "£"
                ),
                Vault(
                    "FNB",
                    "Rand",
                    "R"
                )
            )
        )
    }

    fun index(): List<Vault> {
        return vaults.all()
    }

    fun show(): List<EntryModel> {
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

    fun update() {
        TODO("Add persistence first, since ids keep changing")
    }
}
