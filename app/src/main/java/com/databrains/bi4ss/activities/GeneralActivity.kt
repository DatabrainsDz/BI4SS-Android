package com.databrains.bi4ss.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.databrains.bi4ss.R
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_general.*
import kotlinx.android.synthetic.main.content_general.*


class GeneralActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fillGraph(genderGraphAdjourned, arrayOf(DataPoint(2.0, 40.0)),
                arrayOf(DataPoint(3.0, 60.0)) , "Adjourned" , "Male" , "Female")

        fillGraph(genderGraphAdmitted, arrayOf(DataPoint(2.0, 20.0)),
                arrayOf(DataPoint(3.0, 80.0)) , "Admitted" , "Male" , "Female")

    }

    private fun fillGraph(graph: GraphView,
                          dataOne: Array<DataPoint>,
                          dataTwo: Array<DataPoint>,
                          title: String,
                          legendOne: String,
                          legendTwo: String) {

        val seriesOne = BarGraphSeries<DataPoint>(dataOne)
        val seriesTwo = BarGraphSeries<DataPoint>(dataTwo)

        seriesOne.color = Color.BLUE
        seriesOne.isAnimated = true
        seriesOne.title = legendOne

        seriesTwo.color = Color.RED
        seriesTwo.isAnimated = true
        seriesTwo.title = legendTwo

        graph.addSeries(seriesOne)
        graph.addSeries(seriesTwo)

        graph.viewport.isXAxisBoundsManual = true
        graph.viewport.setMinX(1.0)
        graph.viewport.setMaxX(4.0)
        graph.viewport.isYAxisBoundsManual = true
        graph.viewport.setMinY(0.0)
        graph.viewport.setMaxY(graph.viewport.getMaxY(true) + 20.0)

        graph.title = title
        graph.legendRenderer.isVisible = true
        graph.legendRenderer.align = LegendRenderer.LegendAlign.TOP
        graph.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) return ""
                return super.formatLabel(value, isValueX)
            }
        }

    }
}
