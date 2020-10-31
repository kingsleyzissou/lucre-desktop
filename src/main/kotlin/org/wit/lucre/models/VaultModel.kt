package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils

class VaultModel(
    var name: String,
    var description: String,
    var currency: String,
    var id: String = NanoIdUtils.randomNanoId()
)
