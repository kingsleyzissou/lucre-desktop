package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils

data class EntryModel(
    var amount: Float,
    var type: Enum<EntryType>,
    var vendor: String,
    var description: String,
    var id: String = NanoIdUtils.randomNanoId()
)
