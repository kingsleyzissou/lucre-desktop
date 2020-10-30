package org.wit.lucre.repositories

import org.wit.lucre.models.CategoryModel

class CategoryStore: CRUDRepositoryInterface<CategoryModel> {

    private val categories = HashMap<String, CategoryModel>()

    override fun all(): List<CategoryModel> {
        return categories.values.toList()
    }

    override fun find(id: String): CategoryModel? {
        return categories[id]
    }

    override fun create(category: CategoryModel) {
        categories[category.id] = category
    }

    override fun update(category: CategoryModel) {
        categories[category.id] = category
    }

    override fun addAll(categories: List<CategoryModel>) {
        categories.forEach{ c -> this.create(c) }
    }
}