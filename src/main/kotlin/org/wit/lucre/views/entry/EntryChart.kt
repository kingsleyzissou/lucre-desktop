package org.wit.lucre.views.entry

import javafx.scene.chart.PieChart
import org.wit.lucre.controllers.EntryController
import org.wit.lucre.events.EntriesFilterEvent
import tornadofx.*

class EntryChart : Fragment("Chart View") {
    private val entryController: EntryController by inject()

    private val piechart = piechart("") {
        maxHeight = 100.0
    }

    override val root = piechart

    override fun onDock() {
        super.onDock()
        subscribe<EntriesFilterEvent> { event ->
            clearChart()
            val chartData = entryController.chartData((event.entries))
            for ((key, value) in chartData) {
                var d = PieChart.Data(key.name, value)
                piechart.data.add(d)
                val index = piechart.data.indexOf(d)
                if (index >= 0) {
                    piechart.data[index].node.style = "-fx-pie-color: ${key.hexColor};"
                }
            }
        }
    }

    private fun clearChart() {
        var temp = piechart.data
        piechart.data.removeAll(temp)
    }
}
