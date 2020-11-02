package org.wit.lucre.services

import org.wit.lucre.models.Entry
import org.wit.lucre.models.EntryType

class IncomeService() {

    fun balance(entries: List<Entry>): Float {

        val values = entries.map {
            if (it.type.equals(EntryType.INCOME))
                it.amount
            else it.amount * -1
        }

        return values.reduce { acc, it -> acc + it }
    }
}
