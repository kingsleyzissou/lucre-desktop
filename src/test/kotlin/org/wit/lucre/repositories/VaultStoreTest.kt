package org.wit.lucre.repositories

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.wit.lucre.models.VaultModel

internal class VaultStoreTest {

    private val id1: String = NanoIdUtils.randomNanoId()
    private val id2: String = NanoIdUtils.randomNanoId()
    private val id3: String = NanoIdUtils.randomNanoId()

    private val vault1 = mockk<VaultModel>()
    private val vault2 = mockk<VaultModel>()
    private val vault3 = mockk<VaultModel>()

    private lateinit var store: VaultStore

    @BeforeEach
    fun setup() {
        every { vault1.id } returns id1
        every { vault2.id } returns id2
        every { vault3.id } returns id3

        store = VaultStore()
        store.addAll(listOf(vault1, vault2, vault3))
    }

    @Test
    fun all() {
        assertEquals(3, store.all().size)
    }

    @Test
    fun find() {
        assertEquals(vault1, store.find(id1))
    }

    @Test
    fun create() {
        val id4 = NanoIdUtils.randomNanoId()
        val vault4 = mockk<VaultModel>()
        every { vault4.id } returns id4
        store.create(vault4)
        assertTrue(store.all().contains(vault4))
    }

    @Test
    fun update() {
        val vault5 = mockk<VaultModel>()
        every { vault5.id } returns id3
        every { vault5.name } returns "AIB"
        store.update(vault5)
        assertEquals(vault5, store.find(id3))
    }
}
