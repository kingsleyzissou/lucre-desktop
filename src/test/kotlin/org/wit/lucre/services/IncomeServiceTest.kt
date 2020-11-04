package org.wit.lucre.services

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.wit.lucre.models.Entry
import org.wit.lucre.models.EntryType
import org.wit.lucre.repositories.EntryStore

internal class IncomeServiceTest {

    private val entry1 = mockk<Entry>()
    private val entry2 = mockk<Entry>()
    private val entry3 = mockk<Entry>()
    private val service: IncomeService = IncomeService()

    private lateinit var store: EntryStore

    @BeforeEach
    fun setup() {
        every { entry1.id } returns NanoIdUtils.randomNanoId()
        every { entry1.amount } returns 5F
        every { entry1.type } returns EntryType.EXPENSE
        every { entry1.signedAmount } returns -5F

        every { entry2.id } returns NanoIdUtils.randomNanoId()
        every { entry2.amount } returns 10F
        every { entry2.type } returns EntryType.EXPENSE
        every { entry2.signedAmount } returns -10F

        every { entry3.id } returns NanoIdUtils.randomNanoId()
        every { entry3.amount } returns 15F
        every { entry3.type } returns EntryType.INCOME
        every { entry3.signedAmount } returns 15F

        store = spyk(EntryStore("test.json"))
        every { store["serialize"]() } returns "serialize"
        every { store["deserialize"]() } returns "deserialize"
        store.addAll(listOf(entry1, entry2, entry3))
    }

    @Test
    fun balance() {
        var balance = service.balance(store.all())
        assertEquals(0F, balance)
    }

    @Test
    fun add() {
        val newEntry = spyk<Entry>()
        every { newEntry.id } returns NanoIdUtils.randomNanoId()
        every { newEntry.amount } returns 100F
        every { newEntry.type } returns EntryType.INCOME
        every { newEntry.signedAmount } returns 100F

        store.create(newEntry)

        var balance = service.balance(store.all())
        assertEquals(100F, balance)
    }

    @Test
    fun spend() {
        val newEntry = mockk<Entry>()
        every { newEntry.id } returns NanoIdUtils.randomNanoId()
        every { newEntry.amount } returns 75F
        every { newEntry.type } returns EntryType.EXPENSE
        every { newEntry.signedAmount } returns -75F

        store.create(newEntry)

        var balance = service.balance(store.all())
        assertEquals(-75F, balance)
    }
}
