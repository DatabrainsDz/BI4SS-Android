package com.databrains.bi4ss.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.databrains.bi4ss.R
import com.databrains.bi4ss.adapters.AssociationAdapter
import com.databrains.bi4ss.models.AssociationJson
import com.databrains.bi4ss.models.Response
import com.databrains.bi4ss.webServices.BI4SSWebService
import kotlinx.android.synthetic.main.activity_association.*
import kotlinx.android.synthetic.main.content_association.*
import retrofit2.Call
import retrofit2.Callback


class AssociationActivity : AppCompatActivity(), Callback<AssociationJson> {
    override fun onFailure(call: Call<AssociationJson>, t: Throwable) {
        Log.e("Error", call.request().url().toString())
    }

    override fun onResponse(call: Call<AssociationJson>, response: retrofit2.Response<AssociationJson>) {
        val association = response.body()
        Log.e("Error", association.toString())
        if (association?.data != null) {
            //'14010796554'
            val adapter = AssociationAdapter(association.data)
            val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            associationRecyclerView.layoutManager = manager
            associationRecyclerView.adapter = adapter
            associationProgressBar.visibility = View.GONE
            associationRecyclerView.visibility = View.VISIBLE

        } else {
            Log.e("Error", "Null association response ${call.request().url()}")
        }

    }

    private val webService = BI4SSWebService.retrofit.create(BI4SSWebService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_association)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val currentYear = intent.getStringExtra(GeneralActivity.keyCurrentYear)
        val level = intent.getStringExtra(GeneralActivity.keyLevel)

        webService.getAssociations(currentYear, level).enqueue(this)
    }

}
