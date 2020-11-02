package org.wit.lucre.viewmodels

import org.wit.lucre.models.Category
import tornadofx.ItemViewModel

class CategoryModel : ItemViewModel<Category>() {
    val name = bind(Category::nameProperty)
    val description = bind(Category::descProperty)
    val color = bind(Category::colorProperty)
}
