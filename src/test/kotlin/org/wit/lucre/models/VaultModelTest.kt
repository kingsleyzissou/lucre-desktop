package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

internal class VaultModelTest {

    private val id: String = NanoIdUtils.randomNanoId()

    private lateinit var vault: VaultModel

    private final val EXPECTED_ID: String = id
    private final val EXPECTED_NAME: String = "HSBC"
    private final val EXPECTED_DESCRIPTION: String = "GBP Account"
    private final val EXPECTED_CURRENCY: Char = '£'

    @BeforeEach
    internal fun setup() {
        vault = VaultModel(
            "HSBC",
            "GBP Account",
            '£',
            id
        )
    }

    @Test
    fun getName() {
        assertEquals(EXPECTED_NAME, vault.name)
    }

    @Test
    fun setName() {
        val newName = "AIB"
        vault.name = newName
        assertEquals(newName, vault.name)
    }

    @Test
    fun getDescription() {
        assertEquals(EXPECTED_DESCRIPTION, vault.description)
    }

    @Test
    fun setDescription() {
        val newDescription = "Euro account"
        vault.description = newDescription
        assertEquals(newDescription, vault.description)
    }

    @Test
    fun getCurrency() {
        assertEquals(EXPECTED_CURRENCY, vault.currency)
    }

    @Test
    fun setCurrency() {
        val newCurrency = '€'
        vault.currency = newCurrency
        assertEquals(newCurrency, vault.currency)
    }

    @Test
    fun getId() {
        assertEquals(EXPECTED_ID, vault.id)
    }

    @Test
    fun setId() {
        val newId = NanoIdUtils.randomNanoId()
        vault.id = newId
        assertEquals(newId, vault.id)
    }
}
