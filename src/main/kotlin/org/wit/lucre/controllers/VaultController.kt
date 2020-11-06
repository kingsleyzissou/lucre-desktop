package org.wit.lucre.controllers

import org.wit.lucre.models.Vault
import org.wit.lucre.repositories.VaultStore
import tornadofx.Controller

/**
 * VaultController for presenting
 * vault related views
 */
class VaultController : Controller() {

    /** Vault data store */
    private val store = VaultStore()

    /**
     * Returns a list of all vaults
     */
    fun index(): List<Vault> {
        return store.all()
    }

    /**
     * Finds and returns a vault
     * based on the id
     */
    fun find(id: String): Vault {
        return store.find(id)!!
    }

    /**
     * Method to be used by view for
     * creating a vault
     */
    fun create(vault: Vault) {
        store.create(vault)
    }

    /**
     * Method to be used by view for
     * updating a vault
     */
    fun update(vault: Vault) {
        store.update(vault)
    }

    /**
     * Method to be used by view for
     * deleting a vault
     */
    fun delete(id: String) {
        store.delete(id)
    }
}
