package org.wit.lucre.controllers

import org.wit.lucre.models.Category
import org.wit.lucre.models.Entry
import org.wit.lucre.models.Vault
import org.wit.lucre.repositories.CategoryStore
import org.wit.lucre.repositories.EntryStore
import org.wit.lucre.repositories.VaultStore
import tornadofx.Controller

class VaultController : Controller() {

    private val vaults = VaultStore()
    private val categories = CategoryStore()
    private val entries = EntryStore()

    var category = Category(
        "Takeout",
        "Dine-in and takeaways",
        "#ff0000"
    )

    fun index(): List<Vault> {
//        categories.create(category)
//        entries.create(
//            Entry(
//                10F,
//                EntryType.EXPENSE,
//                "Tesco",
//                "Dinner",
//                category.id,
//                "AzwoXYC9wzohxgvvt7RNe"
//            )
//        )
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
