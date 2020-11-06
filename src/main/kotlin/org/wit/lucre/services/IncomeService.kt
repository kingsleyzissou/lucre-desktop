package org.wit.lucre.services

import org.wit.lucre.models.Category
import org.wit.lucre.models.Entry
import org.wit.lucre.models.EntryType
import org.wit.lucre.repositories.CategoryStore
import java.util.function.Predicate

class IncomeService() {

    private val categoryStore = CategoryStore()

    fun balance(entries: List<Entry>): Float {
        if (entries.isEmpty()) return 0F
        return entries
            .map { it.signedAmount }
            .reduce { acc, it -> acc + it }
    }

    fun expenseCategories(entries: List<Entry>): Map<Category, Double> {
        var predicate = Predicate<Entry> { it.type == EntryType.EXPENSE }
        return entries.filter { predicate.test(it) }
            .groupBy { it.category.id }
            .mapValues { (_, value) ->
                value
                    .map { it.amount.toDouble() }
                    .reduce { acc, it -> acc + it }
            }
            .mapKeys { categoryStore.find(it.key)!! }
    }
}
