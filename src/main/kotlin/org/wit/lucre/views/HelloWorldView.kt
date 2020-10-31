package org.wit.lucre.views

import tornadofx.*

class HelloWorldView() : View("Hello there") {
//    override val root = vbox {
//        button("Press me")
//        label("Waiting")
//    }
    val data = listOf<String>("One", "Two", "Three")

    override val root = datagrid(data) {
//        label(it)
    }
}
