package org.wit.lucre.views

import tornadofx.View
import tornadofx.datagrid

class HelloWorldView() : View("Hello there") {
    private val data = listOf<String>("One", "Two", "Three")

    override val root = datagrid(data) {
        onUserSelect(2) {
            a ->
            println(a)
            println("Some other stuff")
        }
    }
}
