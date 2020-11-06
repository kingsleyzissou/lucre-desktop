package org.wit.lucre.views.category

import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.controllers.CategoryController
import org.wit.lucre.viewmodels.CategoryModel
import tornadofx.*

class CategoryDelete : Fragment("Category Delete") {
    private val categoryController: CategoryController by inject()
    val model: CategoryModel by inject()

    var categoryName = model.name.value

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Back").action { back() }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text("Delete $categoryName:") {
                font = Font(14.0)
            }
        }
        center = form {
            fieldset {
                field("Category name:") {
                    label(model.item.name)
                }
                field("Description:") {
                    label(model.item.description)
                }
                field("Color:") {
                    label(model.item.hexColor)
                }
                field {
                    button("Back") {
                        action { back() }
                        isCancelButton = true
                    }
                    button("Delete") {
                        action {
                            confirm(
                                "Delete vault",
                                "Are you sure?",
                                actionFn = { delete() }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun delete() {
        categoryController.delete(model.item.id)
        var view = find(CategoryIndex::class)
        replaceWith(view, ViewTransition.Slide(0.2.seconds))
    }

    private fun back() {
        val scope = Scope()
        setInScope(model, scope)
        var view = find(CategoryShow::class, scope)
        replaceWith(view, ViewTransition.Slide(0.2.seconds))
    }
}
