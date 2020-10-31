package org.wit.lucre.views

import tornadofx.Fragment
import tornadofx.item
import tornadofx.menu
import tornadofx.menubar


class Menu : Fragment("Menu") {
    override val root = menubar() {
        menu("File") {
            menu("Connect") {
                item("Facebook")
                item("Twitter")
            }
            item("Save")
            item("Quit")
        }
        menu("Edit") {
            item("Copy")
            item("Paste")
        }
    }
}
