package org.wit.lucre.views

import org.wit.lucre.views.category.CategoryIndex
import org.wit.lucre.views.vault.VaultIndex
import tornadofx.*

class AppView() : View("Lucre 1.0") {

    private val vaultIndex = find(VaultIndex::class)
    private val categoryIndex = find(CategoryIndex::class)

    override val root = borderpane() {
        center = drawer {
            item("Vaults", expanded = true) {
                this += vaultIndex.root
            }
            item("Categories", expanded = false) {
                this += categoryIndex.root
            }
        }
    }
}
