package org.wit.lucre.views

import javafx.scene.paint.Color
import tornadofx.View
import tornadofx.borderpane
import tornadofx.label
import tornadofx.style
import tornadofx.useMaxWidth

class HelloWorldView : View("Hello there") {
    override val root = borderpane {
        top = label("TOP") {
            useMaxWidth = true
            style {
                backgroundColor += Color.RED
            }
        }
    }
}
