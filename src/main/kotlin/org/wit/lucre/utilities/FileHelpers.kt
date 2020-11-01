package org.wit.lucre.utilities

import mu.KotlinLogging
import java.io.* // ktlint-disable no-wildcard-imports
import java.lang.Exception

val logger = KotlinLogging.logger {}

fun write(filename: String, data: String) {
    val file = File(filename)
    try {
        val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: Exception) {
        logger.error { "Error writing to file: $e" }
    }
}

fun read(filename: String): String {
    val file = File(filename)
    var str = ""
    try {
        val inputStreamReader = InputStreamReader(FileInputStream(file))
        if (inputStreamReader != null) {
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                var line = bufferedReader.readLine()
                done = (line == null)
                if (line != null) partialStr.append(line)
            }
            inputStreamReader.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        logger.error { "Cannot find file: $e" }
    } catch (e: IOException) {
        logger.error { "Cannot Read file: $e" }
    }
    return str
}

fun fileExists(filename: String): Boolean {
    val file = File(filename)
    return file.exists()
}
