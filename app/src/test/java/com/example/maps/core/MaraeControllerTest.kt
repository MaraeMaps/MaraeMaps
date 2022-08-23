package com.example.maps.core

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Testing class for MaraeController
 *
 * @author Hugo Phibbs
 */
internal class MaraeControllerTest {

    /* Test Marae object */
    private lateinit var marae1: Marae

    @BeforeEach
    fun setUp() {
        marae1 =
            Marae(
                "",
                "0",
                "",
                0,
                "",
                "Hapu 1",
                "Iwi 1",
                "Location 1",
                "Marae 1",
                "",
                "",
                0,
                "",
                "",
                "",
                0.toDouble(),
                0.toDouble()
            )
    }

    @Test
    fun maraeToStringTest() {
        assertEquals("Marae object with name: Marae 1, belonging to Iwi 1, located in Location 1", MaraeController.maraeToString(marae1))
    }

    @Test
    fun keyWordsTest() {
        val expectedKeyWords = arrayOf(marae1.Name, marae1.Iwi, marae1.Location)
        assertArrayEquals(expectedKeyWords, MaraeController.keyWords(marae1))
    }
}