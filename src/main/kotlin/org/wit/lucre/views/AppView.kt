package org.wit.lucre.views

import org.wit.lucre.views.vault.VaultIndex
import tornadofx.View
import tornadofx.borderpane
import tornadofx.drawer
import tornadofx.label

class AppView() : View("Lucre 1.0") {

    private val vaultIndex = find(VaultIndex::class)
    private val menu = find(Menu::class)

    override val root = borderpane() {
        top = menu.root
        center = drawer {
            item("Vaults", expanded = true) {
                borderpane() {
                    center = vaultIndex.root
                }
            }
            item("Categories", expanded = false) {
                label("TODO")
            }
        }
    }
}
