package org.wit.lucre.controllers

import mu.KotlinLogging
import org.wit.lucre.models.VaultModel
import org.wit.lucre.repositories.VaultStore
import tornadofx.Controller

class VaultController : Controller() {

    val vaults = VaultStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Vault controller loading " }

        vaults.addAll(
            listOf(
                VaultModel(
                    "AIB",
                    "Euro",
                    '€'
                ),
                VaultModel(
                    "HSBC",
                    "Pound",
                    '£'
                ),
                VaultModel(
                    "FNB",
                    "Rand",
                    'R'
                )
            )
        )
    }

    fun index(): List<VaultModel> {
        return vaults.all()
    }
}
