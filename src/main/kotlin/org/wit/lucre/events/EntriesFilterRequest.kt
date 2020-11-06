package org.wit.lucre.events

import org.wit.lucre.models.Entry
import tornadofx.EventBus
import tornadofx.FXEvent
import java.util.function.Predicate

/**
 * EventBus class for requesting
 * a filtered list of events
 * based on the desired predicate
 */
class EntriesFilterRequest(
    val predicate: Predicate<Entry>
) : FXEvent(EventBus.RunOn.BackgroundThread)
