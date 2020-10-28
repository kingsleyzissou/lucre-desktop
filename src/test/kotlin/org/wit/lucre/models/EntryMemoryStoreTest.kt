package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class EntryMemoryStoreTest {

    private val id1: String = NanoIdUtils.randomNanoId()
    private val id2: String = NanoIdUtils.randomNanoId()
    private val id3: String = NanoIdUtils.randomNanoId()

    private val entry1 = mockk<EntryModel>()
    private val entry2 = mockk<EntryModel>()
    private val entry3 = mockk<EntryModel>()

    private lateinit var store: EntryMemoryStore

    @BeforeEach
    fun setup() {
        every { entry1.id } returns id1
        every { entry2.id } returns id2
        every { entry3.id } returns id3

        store = EntryMemoryStore()
        store.create(entry1)
        store.create(entry2)
        store.create(entry3)
    }

    @Test
    fun all() {
        assertEquals(3, store.all().size)
    }

    @Test
    fun find() {
        assertEquals(entry1, store.find(id1))
    }

    @Test
    fun create() {
        val id4 = NanoIdUtils.randomNanoId()
        val entry4 = mockk<EntryModel>()
        every { entry4.id } returns id4
        store.create(entry4)
        assertTrue(store.all().contains(entry4))
    }

    @Test
    fun update() {
        val entry5 = mockk<EntryModel>()
        every { entry5.id } returns id3
        every { entry5.vendor } returns "Tesco"
        store.update(entry5)
        assertEquals(entry5, store.find(id3))
    }
}
