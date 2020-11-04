package org.wit.lucre.views.category

import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import org.wit.lucre.controllers.CategoryController
import org.wit.lucre.viewmodels.CategoryModel
import tornadofx.*

class CategoryEdit : Fragment("Edit Category") {
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
            text("Edit $categoryName:") {
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
                    button("Edit") {
                        enableWhen(model.valid)
                        isDefaultButton = true
                        action {
                            model.commit()
                            update()
                        }
                    }
                }
            }
        }
    }

    private fun update() {
        categoryController.update(model.item)
        back()
    }

    private fun back() {
        val scope = Scope()
        setInScope(model, scope)
        var view = find(CategoryShow::class, scope)
        replaceWith(view, ViewTransition.Slide(0.2.seconds))
    }
}
