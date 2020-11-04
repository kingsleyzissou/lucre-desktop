package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import org.wit.lucre.repositories.CategoryStore
import tornadofx.*
import java.time.LocalDateTime
import javax.json.JsonObject

class Entry(
    amount: Float? = null,
    type: Enum<EntryType>? = null,
    vendor: String? = null,
    description: String? = null,
    category: Category? = null,
    vault: String? = null,
    override var id: String = NanoIdUtils.randomNanoId(),
    date: LocalDateTime = LocalDateTime.now()
) : Model() {
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
    var date: LocalDateTime by property(date)

    fun getSignedAmount(): Float {
        if (type == EntryType.INCOME) {
            return amount
        }
        return amount * -1
    }

    private fun categoryFromId(id: String): Category {
        val store = CategoryStore()
        return store.find(id)!!
    }

    override fun updateModel(json: JsonObject) {
        with(json) {
            amount = string("amount").toString().toFloat()
            type = EntryType.valueOf(string("type").toString())
            vendor = string("vendor").toString()
            description = string("description").toString()
            vault = string("vault").toString()
            date = datetime("date")!!
            id = string("id").toString()
            category = categoryFromId(string("category").toString())
        }
    }

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
