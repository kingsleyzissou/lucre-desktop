package org.wit.lucre.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class CategoryModelTest {

    lateinit var category: CategoryModel;

    final val EXPECTED_NAME = "Takeout"
    final val EXPECTED_DESCRIPTION = "Dine-in and takeaways"
    final val EXPECTED_COlOR = "#ff0000"

    @BeforeEach
    internal fun setup() {
        category = CategoryModel(
            "Takeout",
            "Dine-in and takeaways",
            "#ff0000"
        )
    }

    @Test
    fun getName() {
        assertEquals(EXPECTED_NAME, category.name)
    }

    @Test
    fun setName() {
        var newName = "Bills"
        category.name = newName
        assertEquals(newName, category.name)
    }

    @Test
    fun getDescription() {
        assertEquals(EXPECTED_DESCRIPTION, category.description)
    }

    @Test
    fun setDescription() {
        var newDescription = "Utilities and lights"
        category.description = newDescription
        assertEquals(newDescription, category.description)
    }

    @Test
    fun getColor() {
        assertEquals(EXPECTED_COlOR, category.color)
    }

    @Test
    fun setColor() {
        var newColor = "#00ff00"
        category.color = newColor
        assertEquals(newColor, category.color)
    }
}