package org.wit.lucre.controllers

import org.wit.lucre.models.Category
import org.wit.lucre.repositories.CategoryStore
import tornadofx.Controller

/**
 * CategoryController for
 * presenting category related
 * views
 */
class CategoryController : Controller() {

    /** Category datastore. */
    private val store = CategoryStore()

    /**
     * Function to pass a list of all categories to
     * the view.
     */
    fun index(): List<Category> {
        return store.all()
    }

    /**
     * Method for view to create a category
     */
    fun create(category: Category) {
        store.create(category)
    }

    /**
     * Method for view to update a category
     */
    fun update(category: Category) {
        store.update(category)
    }

    /**
     * Method for view to delete a category
     */
    fun delete(id: String) {
        store.delete(id)
    }
}
