package org.wit.lucre.utilities

import javafx.scene.paint.Color
import kotlin.math.roundToInt

// https://stackoverflow.com/a/56733608
private fun format(value: Double): String {
    val integer = Integer.toHexString((value * 255).roundToInt())
    if (integer.length == 1) "0$integer".toUpperCase()
    return integer.toUpperCase()
}

// https://stackoverflow.com/a/56733608
fun toHexString(value: Color): String? {
    val red = format(value.red)
    val green = format(value.green)
    val blue = format(value.blue)
    return "#$red$green$blue"
}
