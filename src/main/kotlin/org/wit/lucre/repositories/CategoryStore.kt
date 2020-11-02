package org.wit.lucre.repositories

import org.wit.lucre.models.Category

class CategoryStore : CRUDRepositoryInterface<Category> {

    private val categories = HashMap<String, Category>()

    override fun all(): List<Category> {
        return categories.values.toList()
    }

    override fun find(id: String): Category? {
        return categories[id]
    }

    override fun create(value: Category) {
        categories[value.id] = value
    }

    override fun update(value: Category) {
        categories[value.id] = value
    }

    override fun addAll(values: List<Category>) {
        values.forEach { c -> this.create(c) }
    }
}
