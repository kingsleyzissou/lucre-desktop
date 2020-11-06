package org.wit.lucre.main

import mu.KotlinLogging
import tornadofx.launch

/**
 * Custom logger that was used during development
 * for debugging
 *
 * The logger is now limited to printing a statement
 * when the app is being launched
 */
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    logger.info { "Lucre Kotlin Desktop App - Version 1.0" }
    launch<Lucre>(args)
}
