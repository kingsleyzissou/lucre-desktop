package org.wit.lucre.services

import org.wit.lucre.models.Entry

class IncomeService() {

    fun balance(entries: List<Entry>): Float {
        return entries
            .map { it.getSignedAmount() }
            .reduce { acc, it -> acc + it }
    }
}
