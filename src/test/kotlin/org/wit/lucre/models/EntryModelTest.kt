package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class EntryModelTest {

    lateinit var expense: EntryModel

    val id: String = NanoIdUtils.randomNanoId()

    final val EXPECTED_ID: String = id
    final val EXPECTED_VENDOR: String = "Tesco"
    final val EXPECTED_DESCRIPTION: String = "Dinner"
    final val EXPECTED_AMOUNT: Float = 10F
    final val EXPECTED_TYPE: Enum<EntryType> = EntryType.EXPENSE

    @BeforeEach
    internal fun setup() {
        expense = EntryModel(
            10F,
            EntryType.EXPENSE,
            "Tesco",
            "Dinner",
            id
        )
    }

    @Test
    fun getId() {
        assertEquals(EXPECTED_ID, expense.id)
    }

    @Test
    fun setId() {
        var newId: String = NanoIdUtils.randomNanoId()
        expense.id = newId
        assertEquals(newId, expense.id)
    }

    @Test
    fun getAmount() {
        assertEquals(EXPECTED_AMOUNT, expense.amount)
    }

    @Test
    fun setAmount() {
        var newAmount: Float = 15F
        expense.amount = newAmount
        assertEquals(newAmount, expense.amount)
    }

    @Test
    fun getType() {
        assertEquals(EXPECTED_TYPE, expense.type)
    }

    @Test
    fun setType() {
        var newType = EntryType.INCOME
        expense.type = newType
        assertEquals(newType, expense.type)
    }

    @Test
    fun getVendor() {
        assertEquals(EXPECTED_VENDOR, expense.vendor)
    }

    @Test
    fun setVendor() {
        var newVendor: String = "Spar"
        expense.vendor = newVendor
        assertEquals(newVendor, expense.vendor)
    }

    @Test
    fun getDescription() {
        assertEquals(EXPECTED_DESCRIPTION, expense.description)
    }

    @Test
    fun setDescription() {
        var newDescription: String = "Refund"
        expense.description = newDescription
        assertEquals(newDescription, expense.description)
    }
}
