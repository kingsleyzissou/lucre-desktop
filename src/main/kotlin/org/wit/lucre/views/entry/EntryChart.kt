package org.wit.lucre.views.entry

import javafx.scene.chart.PieChart
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.events.EntriesFilterEvent
import tornadofx.*

class EntryChart : Fragment("Chart View") {
    private val entryController: EntryController by inject()

    override val root = piechart("") {
        maxHeight = 100.0
        subscribe<EntriesFilterEvent> { event ->
            val chartData = entryController.chartData((event.entries))
            for ((key, value) in chartData) {
                var d = PieChart.Data(key.name, value)
                data.add(d)
                val index = data.indexOf(d)
                data[index].node.style = "-fx-pie-color: ${key.hexColor};"
            }
        }
    }

}
