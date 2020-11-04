package org.wit.lucre.models

import javafx.scene.paint.Color
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CategoryModelTest {

    lateinit var category: Category

    private final val EXPECTED_NAME = "Takeout"
    private final val EXPECTED_DESCRIPTION = "Dine-in and takeaways"
    private final val EXPECTED_COlOR = Color.web("#ff0000")

    @BeforeEach
    internal fun setup() {
        category = Category(
            "Takeout",
            "Dine-in and takeaways",
            Color.web("#ff0000")
        )
    }

    @Test
    fun getName() {
        assertEquals(EXPECTED_NAME, category.name)
    }

    @Test
    fun setName() {
        val newName = "Bills"
        category.name = newName
        assertEquals(newName, category.name)
    }

    @Test
    fun getDescription() {
        assertEquals(EXPECTED_DESCRIPTION, category.description)
    }

    @Test
    fun setDescription() {
        val newDescription = "Utilities and lights"
        category.description = newDescription
        assertEquals(newDescription, category.description)
    }

    @Test
    fun getColor() {
        assertEquals(EXPECTED_COlOR, category.color)
    }

    @Test
    fun setColor() {
        val newColor = Color.web("#00ff00")
        category.color = newColor
        assertEquals(newColor, category.color)
    }
}
