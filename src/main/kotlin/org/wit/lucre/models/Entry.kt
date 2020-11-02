package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import tornadofx.getProperty
import tornadofx.property
import java.time.LocalDateTime

class Entry(
    amount: Float? = null,
    type: Enum<EntryType>? = null,
    vendor: String? = null,
    description: String? = null,
    category: String? = null,
    vault: String? = null,
    date: LocalDateTime? = null,
    var id: String = NanoIdUtils.randomNanoId()
) {
    fun amountProperty() = getProperty(Entry::amount)
    fun typeProperty()  =  getProperty(Entry::type)
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
}
