package org.wit.lucre.views

import javafx.scene.paint.Color
import tornadofx.*

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
