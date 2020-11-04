package org.wit.lucre.views.category

import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import org.wit.lucre.controllers.CategoryController
import org.wit.lucre.models.Category
import org.wit.lucre.viewmodels.CategoryModel
import tornadofx.*

class CategoryCreate : View("Create Category") {
    private val categoryController: CategoryController by inject()
    private var model = CategoryModel(Category())

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Back").action { back() }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text("Create vault:") {
                font = Font(14.0)
            }
        }
        center = form {
            fieldset {
                field("Category name:") {
                    textfield(model.name).required()
                }
                field("Description:") {
                    textfield(model.description)
                }
                field("Color:") {
                    colorpicker(model.color as SimpleObjectProperty<Color>).required()
                }
                field {
                    button("Clear") {
                        action { model.rollback() }
                        isCancelButton = true
                    }
                    button("Create") {
                        enableWhen(model.valid)
                        isDefaultButton = true
                        action {
                            model.commit()
                            create()
                        }
                    }
                }
            }
        }
    }

    private fun create() {
        categoryController.create(model.item)
        back()
    }

    private fun back() {
        var view = find(CategoryIndex::class)
        replaceWith(view, ViewTransition.FadeThrough(0.4.seconds))
    }
}
