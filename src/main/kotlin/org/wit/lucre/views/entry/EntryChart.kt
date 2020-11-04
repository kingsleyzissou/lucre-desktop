package org.wit.lucre.views.entry

import org.wit.lucre.controllers.EntryController
import org.wit.lucre.events.EntriesFilterEvent
import tornadofx.*

class EntryChart : Fragment("My View") {
    private val entryController: EntryController by inject()

    override val root = piechart("") {
        maxHeight = 100.0
        subscribe<EntriesFilterEvent> { event ->
            val d = entryController.chartData((event.entries))
            data(d)
        }
    }
}
