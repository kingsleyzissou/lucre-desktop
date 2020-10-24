package org.wit.lucre.models

data class EntryModel(
    var amount: Int,
    var type: Enum<EntryType>,
    var description: String
)