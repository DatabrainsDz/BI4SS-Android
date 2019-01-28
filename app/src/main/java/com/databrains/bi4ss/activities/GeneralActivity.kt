package com.databrains.bi4ss.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.databrains.bi4ss.R
import com.jjoe64.graphview.DefaultLabelFormatter
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

        val seriesOne = BarGraphSeries<DataPoint>(arrayOf(
                DataPoint(1.0, 20.0),
                DataPoint(2.0, 30.0)
        ))

        seriesOne.color = Color.BLUE
        seriesOne.isAnimated = true

        seriesOne.title = "Admitted"

        val seriesTwo = BarGraphSeries<DataPoint>(
                arrayOf(DataPoint(3.0, 40.0),
                        DataPoint(4.0, 20.0)))
        seriesTwo.color = Color.RED
        seriesTwo.isAnimated = true

        seriesTwo.title = "Adjourned"

        genderGraph.addSeries(seriesOne)
        genderGraph.addSeries(seriesTwo)

        genderGraph.viewport.isXAxisBoundsManual = true
        genderGraph.viewport.setMinX(0.0)
        genderGraph.viewport.setMaxX(6.0)

        genderGraph.viewport.isYAxisBoundsManual = true
        genderGraph.viewport.setMinY(0.0)
        genderGraph.viewport.setMaxY(50.0)

        genderGraph.legendRenderer.isVisible = true
        genderGraph.legendRenderer.align = LegendRenderer.LegendAlign.TOP
        genderGraph.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) {
                    return when (value) {
                        1.5 -> "Male"
                        3.5 -> "Female"
                        else -> super.formatLabel(value, isValueX)
                    }
                }
                return super.formatLabel(value, isValueX)
            }
        }
    }

}
