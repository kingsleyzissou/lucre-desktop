package org.wit.lucre.models

import com.aventrix.jnanoid.jnanoid.NanoIdUtils

class CategoryModel(
    var name: String,
    var description: String,
    var color: String,
    var id: String = NanoIdUtils.randomNanoId()
)
