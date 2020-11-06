package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import org.wit.lucre.repositories.CategoryStore
import tornadofx.*
import java.time.LocalDate
import javax.json.JsonObject

/**
 * Entry model class for the apps income and expense
 * entries. An entry comprises of a vendor, amount,
 * expense type, description, category, vault
 * and date
 */
class Entry(
    amount: Float? = null,
    type: Enum<EntryType>? = null,
    vendor: String? = null,
    description: String? = null,
    category: Category? = null,
    vault: String? = null,
    override var id: String = NanoIdUtils.randomNanoId(),
    date: LocalDate = LocalDate.now()
) : Model() {
    /** Properties for TornadoFX ViewModels */
    fun amountProperty() = getProperty(Entry::amount)
    fun typeProperty() = getProperty(Entry::type)
    fun vendorProperty() = getProperty(Entry::vendor)
    fun descProperty() = getProperty(Entry::description)
    fun categoryProperty() = getProperty(Entry::category)
    fun vaultProperty() = getProperty(Entry::vault)
    fun dateProperty() = getProperty(Entry::date)

    var amount: Float by property(amount)
    var type: Enum<EntryType> by property(type)
    var vendor: String by property(vendor)
    var description: String by property(description)
    var category: Category by property(category)
    var vault: String by property(vault)
    var date: LocalDate by property(date)

    /**
     * Computed method to get the amount with
     * the correct sign. If the entry is an expense,
     * the value is negative. If it is income,
     * the amount will be positive.
     */
    val signedAmount: Float get() {
        if (type == EntryType.INCOME) {
            return amount
        }
        return amount * -1
    }

    /**
     * The entry object requires the entire category,
     * but for serialization purposes, only the category
     * id is saved. This method returns the correct category
     * for the given id
     */
    private fun categoryFromId(id: String): Category {
        val store = CategoryStore()
        return store.find(id)!!
    }

    /**
     * JsonModel class for deserializing JSON fields
     * back to the Category model. Each field has a custom action to convert the
     * JSON object field back to the desired type. This method overrides
     * the JSONModel TornadoFX `updateModel` function
     */
    override fun updateModel(json: JsonObject) {
        with(json) {
            amount = string("amount").toString().toFloat()
            type = EntryType.valueOf(string("type").toString())
            vendor = string("vendor").toString()
            description = string("description").toString()
            vault = string("vault").toString()
            date = date("date")!!
            id = string("id").toString()
            category = categoryFromId(string("category").toString())
        }
    }

    /**
     * Method for serializing category fields
     * down to JSON strings. This method overrides
     * the JSONModel TornadoFX `toJSON` function
     */
    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("amount", amount.toString())
            add("type", type.toString())
            add("vendor", vendor)
            add("description", description)
            add("category", category.id)
            add("vault", vault)
            add("date", date)
            add("id", id)
        }
    }
}
