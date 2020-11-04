package org.wit.lucre.events

import org.wit.lucre.models.Entry
import tornadofx.FXEvent

class EntriesFilterEvent(val entries: List<Entry>) : FXEvent()
