package org.wit.lucre.events

import org.wit.lucre.models.Entry
import tornadofx.FXEvent

/**
 * EventBus class for returning
 * a filtered list of events
 *
 */
class EntriesFilterEvent(val entries: List<Entry>) : FXEvent()
