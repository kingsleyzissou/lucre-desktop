package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils
import java.time.LocalDateTime

data class EntryModel(
    var amount: Float,
    var type: Enum<EntryType>,
    var vendor: String,
    var description: String,
    var category: CategoryModel,
    var vault: Vault,
    var id: String = NanoIdUtils.randomNanoId(),
    var date: LocalDateTime = LocalDateTime.now()
)
