package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class EntryModelTest {

    lateinit var expense: EntryModel

    private val id: String = NanoIdUtils.randomNanoId()
    private val dateTime: LocalDateTime = LocalDateTime.now()
    private val vault: VaultModel = VaultModel(
        "HSBC",
        "GBP Account",
        "£"
    )
    private val category: CategoryModel = CategoryModel(
        "Takeout",
        "Dine-in and takeaways",
        "#ff0000"
    )

    private final val EXPECTED_ID: String = id
    private final val EXPECTED_VENDOR: String = "Tesco"
    private final val EXPECTED_DESCRIPTION: String = "Dinner"
    private final val EXPECTED_AMOUNT: Float = 10F
    private final val EXPECTED_TYPE: Enum<EntryType> = EntryType.EXPENSE
    private final val EXPECTED_DATE: LocalDateTime = dateTime
    private final val EXPECTED_CATEGORY: CategoryModel = category
    private final val EXPECTED_VAULT: VaultModel = vault

    @BeforeEach
    internal fun setup() {
        expense = EntryModel(
            10F,
            EntryType.EXPENSE,
            "Tesco",
            "Dinner",
            category,
            vault,
            id,
            dateTime
        )
    }

    @Test
    fun getId() {
        assertEquals(EXPECTED_ID, expense.id)
    }

    @Test
    fun setId() {
        val newId: String = NanoIdUtils.randomNanoId()
        expense.id = newId
        assertEquals(newId, expense.id)
    }

    @Test
    fun getAmount() {
        assertEquals(EXPECTED_AMOUNT, expense.amount)
    }

    @Test
    fun setAmount() {
        val newAmount = 15F
        expense.amount = newAmount
        assertEquals(newAmount, expense.amount)
    }

    @Test
    fun getType() {
        assertEquals(EXPECTED_TYPE, expense.type)
    }

    @Test
    fun setType() {
        val newType = EntryType.INCOME
        expense.type = newType
        assertEquals(newType, expense.type)
    }

    @Test
    fun getVendor() {
        assertEquals(EXPECTED_VENDOR, expense.vendor)
    }

    @Test
    fun setVendor() {
        val newVendor = "Spar"
        expense.vendor = newVendor
        assertEquals(newVendor, expense.vendor)
    }

    @Test
    fun getDescription() {
        assertEquals(EXPECTED_DESCRIPTION, expense.description)
    }

    @Test
    fun setDescription() {
        val newDescription = "Refund"
        expense.description = newDescription
        assertEquals(newDescription, expense.description)
    }

    @Test
    fun getCategory() {
        assertEquals(EXPECTED_CATEGORY, expense.category)
    }

    @Test
    fun setCategory() {
        val newCategory = CategoryModel(
            "Bills",
            "Household bills",
            "#fff"
        )
        expense.category = newCategory
        assertEquals(newCategory, expense.category)
    }

    @Test
    fun getVault() {
        assertEquals(EXPECTED_VAULT, expense.vault)
    }

    @Test
    fun setVault() {
        val newVault = VaultModel(
            "AIB",
            "Euro account",
            "€"
        )
        expense.vault = newVault
        assertEquals(newVault, expense.vault)
    }

    @Test
    fun getDate() {
        assertEquals(EXPECTED_DATE, expense.date)
    }

    @Test
    fun setDate() {
        val newDate: LocalDateTime = LocalDateTime.now()
        expense.date = newDate
        assertEquals(newDate, expense.date)
    }
}
