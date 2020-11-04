package org.wit.lucre.services

import org.wit.lucre.models.Entry
import org.wit.lucre.models.EntryType
import java.util.function.Predicate

class IncomeService() {

    fun balance(entries: List<Entry>): Float {
        return entries
            .map { it.getSignedAmount() }
            .reduce { acc, it -> acc + it }
    }

    fun expenseCategories(entries: List<Entry>): Map<String, Double> {
        var predicate = Predicate<Entry> { it.type == EntryType.EXPENSE }
        return entries.filter { predicate.test(it) }
            .groupBy { it.category }
            .mapKeys { it.key.name }
            .mapValues {
                e ->
                e.value
                    .map { it.amount.toDouble() }
                    .reduce { acc, it -> acc + it }
            }
    }
}
