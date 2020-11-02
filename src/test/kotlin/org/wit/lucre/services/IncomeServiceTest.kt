package org.wit.lucre.services

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
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

        every { entry2.id } returns NanoIdUtils.randomNanoId()
        every { entry2.amount } returns 10F
        every { entry2.type } returns EntryType.EXPENSE

        every { entry3.id } returns NanoIdUtils.randomNanoId()
        every { entry3.amount } returns 15F
        every { entry3.type } returns EntryType.INCOME

        store = spyk(recordPrivateCalls = true)
        every { store["serialize"]() } returns println("serialize")
        every { store["deserialize"]() } returns println("deserialize")
        store.create(entry1)
        store.create(entry2)
        store.create(entry3)
    }

    @Test
    fun balance() {
        var balance = service.balance(store.all())
        assertEquals(0F, balance)
    }

    @Test
    fun add() {
        val newEntry = mockk<Entry>()
        every { newEntry.id } returns NanoIdUtils.randomNanoId()
        every { newEntry.amount } returns 100F
        every { newEntry.type } returns EntryType.INCOME

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

        store.create(newEntry)

        var balance = service.balance(store.all())
        assertEquals(-75F, balance)
    }
}
