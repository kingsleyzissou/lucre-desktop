package org.wit.lucre.events

import org.wit.lucre.models.Entry
import tornadofx.EventBus
import tornadofx.FXEvent
import java.util.function.Predicate

// object EntriesFilterRequest :FXEvent(EventBus.RunOn.BackgroundThread)
class EntriesFilterRequest(val predicate: Predicate<Entry>) : FXEvent(EventBus.RunOn.BackgroundThread)
