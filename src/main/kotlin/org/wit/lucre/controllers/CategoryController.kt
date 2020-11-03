package org.wit.lucre.controllers

import org.wit.lucre.models.Category
import org.wit.lucre.repositories.CategoryStore
import tornadofx.Controller

class CategoryController: Controller() {

    private val store = CategoryStore()

    fun index(): List<Category> {
        return store.all()
    }
}