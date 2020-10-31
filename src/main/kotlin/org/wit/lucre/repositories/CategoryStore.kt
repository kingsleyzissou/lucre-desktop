package org.wit.lucre.repositories

import org.wit.lucre.models.CategoryModel

class CategoryStore : CRUDRepositoryInterface<CategoryModel> {

    private val categories = HashMap<String, CategoryModel>()

    override fun all(): List<CategoryModel> {
        return categories.values.toList()
    }

    override fun find(id: String): CategoryModel? {
        return categories[id]
    }

    override fun create(value: CategoryModel) {
        categories[value.id] = value
    }

    override fun update(value: CategoryModel) {
        categories[value.id] = value
    }

    override fun addAll(values: List<CategoryModel>) {
        values.forEach { c -> this.create(c) }
    }
}
