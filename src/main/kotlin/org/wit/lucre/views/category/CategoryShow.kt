package org.wit.lucre.views.category

import javafx.geometry.Pos
import javafx.scene.text.Font
import org.wit.lucre.utilities.toHexString
import org.wit.lucre.viewmodels.CategoryModel
import tornadofx.*

class CategoryShow : Fragment("Show Category") {
    val model: CategoryModel by inject()

    override val root = borderpane {
        top = vbox {
            vboxConstraints { marginBottom = 20.0 }
            hbox {
                button("Back").action { switch("") }
                button("Edit").action { switch("edit") }
                alignment = Pos.TOP_RIGHT
            }
            separator { }
            text(model.name) {
                font = Font(20.0)
            }
        }
        center = form {
            fieldset {
                field("Category name:") {
                    label(model.name.value)
                }
                field("Description:") {
                    label(model.description.value)
                }
                field("Color:") {
                    val hexString = toHexString(model.color.value)
                    label(hexString!!)
                }
            }
        }
    }

    private fun switch(action: String?) {
        val scope = Scope()
        setInScope(model, scope)
        var view = when (action) {
            ("edit") -> find(CategoryEdit::class, scope)
            else -> find(CategoryIndex::class)
        }
        replaceWith(view, ViewTransition.Slide(0.2.seconds))
    }
}
