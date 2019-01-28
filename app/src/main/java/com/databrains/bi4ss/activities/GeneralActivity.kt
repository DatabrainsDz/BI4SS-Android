package com.databrains.bi4ss.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.databrains.bi4ss.R
import com.databrains.bi4ss.models.StatisticsResponse
import com.databrains.bi4ss.webServices.BI4SSWebService
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_general.*
import kotlinx.android.synthetic.main.content_general.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GeneralActivity : AppCompatActivity(), Callback<StatisticsResponse> {
    override fun onFailure(call: Call<StatisticsResponse>, t: Throwable) {
        Log.e("Error", t.message)
    }

    override fun onResponse(call: Call<StatisticsResponse>, response: Response<StatisticsResponse>) {
        fillGraph(genderGraphAdjourned, arrayOf(DataPoint(2.0, 40.0)),
                arrayOf(DataPoint(3.0, 60.0)), "Adjourned", "Male", "Female")

        fillGraph(genderGraphAdmitted, arrayOf(DataPoint(2.0, 20.0)),
                arrayOf(DataPoint(3.0, 80.0)), "Admitted", "Male", "Female")
    }

    companion object {
        const val keyScholarYear = "keyScholarYear"
        const val keyLevel = "keyLevel"
        const val keyCurrentYear = "keyCurrentYear"
    }

    private val webService = BI4SSWebService.retrofit.create(BI4SSWebService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)
        setSupportActionBar(toolbar)

        val currentYear = intent.getStringExtra(keyCurrentYear)
        val scholarYear = intent.getStringExtra(keyScholarYear)
        val level = intent.getStringExtra(keyLevel)

        webService.getStatistics(scholarYear, level, currentYear).enqueue(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fillGraph(genderGraphAdjourned, arrayOf(DataPoint(2.0, 40.0)),
                arrayOf(DataPoint(3.0, 60.0)), "Adjourned", "Male", "Female")

        fillGraph(genderGraphAdmitted, arrayOf(DataPoint(2.0, 20.0)),
                arrayOf(DataPoint(3.0, 80.0)), "Admitted", "Male", "Female")
        fillGraph(nationalityGraphAdjourned, arrayOf(DataPoint(2.0, 40.0)),
                arrayOf(DataPoint(3.0, 60.0)), "Adjourned", "Algerian", "Others")

        fillGraph(nationalityGraphAdjourned, arrayOf(DataPoint(2.0, 20.0)),
                arrayOf(DataPoint(3.0, 80.0)), "Admitted", "Algerian", "Others")

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_statistics, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.association_item) {
            val intent = Intent(this, GeneralActivity::class.java)
            intent.putExtra(GeneralActivity.keyCurrentYear, intent.getStringExtra(keyCurrentYear))
            intent.putExtra(GeneralActivity.keyLevel, intent.getStringExtra(keyLevel))
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
