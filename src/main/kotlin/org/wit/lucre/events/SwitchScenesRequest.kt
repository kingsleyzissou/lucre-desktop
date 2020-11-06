package org.wit.lucre.events

import tornadofx.*

class SwitchScenesRequest(
    val fragment: Fragment,
    val transition: ViewTransition
) : FXEvent()
