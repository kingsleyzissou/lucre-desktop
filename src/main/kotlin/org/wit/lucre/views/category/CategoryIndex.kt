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
                    action { switch(null, "create") }
                }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
        }
        center = datagrid(categories) {
            cellCache {
                label(it.name)
            }
            onUserSelect(2) { switch(it, "show") }
            contextmenu {
                item("Edit").action {
                    if (selectedItem != null) {
                        switch(selectedItem!!, "edit")
                    }
                }
                item("Delete").action {
                    if (selectedItem != null) {
                        switch(selectedItem!!, "delete")
                    }
                }
            }
        }
    }

    private fun switch(category: Category?, action: String) {
        val scope = Scope()
        if (category != null) {
            val model = CategoryModel(category)
            setInScope(model, scope)
        }
        var view = when (action) {
            "show" -> find(CategoryShow::class, scope)
            "edit" -> find(CategoryEdit::class, scope)
            "delete" -> find(CategoryDelete::class, scope)
            else -> find(CategoryCreate::class, scope)
        }
        replaceWith(view, ViewTransition.FadeThrough(0.5.seconds))
    }
}
