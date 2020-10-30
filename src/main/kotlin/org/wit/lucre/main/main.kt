package org.wit.lucre.main

import mu.KotlinLogging
import tornadofx.launch

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    logger.info { "Launching Lucre App" }
    println("Lucre Kotlin Desktop App - Version 1.0")
    launch<Lucre>(args)
}
