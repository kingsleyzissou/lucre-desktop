package org.wit.lucre.views.category

import javafx.geometry.Pos
import org.wit.lucre.controllers.CategoryController
import org.wit.lucre.models.Category
import org.wit.lucre.viewmodels.CategoryModel
import tornadofx.*

class CategoryIndex : Fragment("Category List") {
    val categoryController: CategoryController by inject()

    var categories = categoryController.index().toObservable()

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Create") {
                    action { switch(null) }
                }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
        }
        center = datagrid(categories) {
            cellCache {
                label(it.name)
            }
            onUserSelect(2) { switch(it) }
        }
    }

    private fun switch(category: Category?) {
        if (category == null) {
            var view = find(CategoryCreate::class)
            replaceWith(view, ViewTransition.FadeThrough(0.5.seconds))
            return
        }
        val model = CategoryModel(category)
        val scope = Scope()
        setInScope(model, scope)
        var view = find(CategoryShow::class, scope)
        replaceWith(view, ViewTransition.FadeThrough(0.5.seconds))
    }
}
