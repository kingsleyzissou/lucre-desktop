package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import tornadofx.* // ktlint-disable no-wildcard-imports
import java.time.LocalDateTime
import javax.json.JsonObject

class Entry(
    amount: Float? = null,
    type: Enum<EntryType>? = null,
    vendor: String? = null,
    description: String? = null,
    category: String? = null,
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
    var category: String by property(category)
    var vault: String by property(vault)
    var date: LocalDateTime by property(date)

    override fun updateModel(json: JsonObject) {
        with(json) {
            amount = float("amount")!!
            type = enumValueOf<EntryType>("type")
            vendor = string("vendor").toString()
            description = string("description").toString()
            category = string("category").toString()
            date = datetime("date")!!
            id = string("id").toString()
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("amount", amount)
            add("type", type.toString())
            add("vendor", vendor)
            add("description", description)
            add("category", category)
            add("vault", vault)
            add("date", date)
            add("id", id)
        }
    }
}
