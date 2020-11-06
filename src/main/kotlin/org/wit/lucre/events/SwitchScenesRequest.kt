package org.wit.lucre.events

import tornadofx.*

/**
 * Event request to switch scenes
 * with the new view component and
 * desired transition. Used only
 * in the view components
 */
class SwitchScenesRequest(
    val fragment: Fragment,
    val transition: ViewTransition
) : FXEvent()
