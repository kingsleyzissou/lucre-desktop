package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils

class VaultModel(
    var name: String,
    var description: String,
    var currency: Char,
    var id: String = NanoIdUtils.randomNanoId()
)
