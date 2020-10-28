package org.wit.lucre.services

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.wit.lucre.models.EntryMemoryStore
import org.wit.lucre.models.EntryModel
import org.wit.lucre.models.EntryType

internal class IncomeServiceTest {

    private val entry1 = mockk<EntryModel>()
    private val entry2 = mockk<EntryModel>()
    private val entry3 = mockk<EntryModel>()
    private val service: IncomeService = IncomeService()

    private lateinit var store: EntryMemoryStore

    @BeforeEach
    fun setup() {
        every { entry1.id } returns NanoIdUtils.randomNanoId()
        every { entry1.amount } returns 5
        every { entry1.type } returns EntryType.EXPENSE

        every { entry2.id } returns NanoIdUtils.randomNanoId()
        every { entry2.amount } returns 10
        every { entry2.type } returns EntryType.EXPENSE

        every { entry3.id } returns NanoIdUtils.randomNanoId()
        every { entry3.amount } returns 15
        every { entry3.type } returns EntryType.INCOME

        store = EntryMemoryStore()
        store.create(entry1)
        store.create(entry2)
        store.create(entry3)
    }

    @Test
    fun balance() {
        var balance = service.balance(store.all())
        assertEquals(0, balance)
    }

    @Test
    fun add() {
        val newEntry = mockk<EntryModel>()
        every { newEntry.id } returns NanoIdUtils.randomNanoId()
        every { newEntry.amount } returns 100
        every { newEntry.type } returns EntryType.INCOME

        store.create(newEntry)

        var balance = service.balance(store.all())
        assertEquals(100, balance)
    }

    @Test
    fun spend() {
        val newEntry = mockk<EntryModel>()
        every { newEntry.id } returns NanoIdUtils.randomNanoId()
        every { newEntry.amount } returns 75
        every { newEntry.type } returns EntryType.EXPENSE

        store.create(newEntry)

        var balance = service.balance(store.all())
        assertEquals(-75, balance)
    }
}
