package org.wit.lucre.viewmodels

import org.wit.lucre.models.Category
import tornadofx.ItemViewModel

/**
 * TornadoFX ViewModel for corresponding Category model
 */
class CategoryModel(category: Category) : ItemViewModel<Category>(category) {
    val name = bind(Category::nameProperty)
    val description = bind(Category::descProperty)
    val color = bind(Category::colorProperty)
}
