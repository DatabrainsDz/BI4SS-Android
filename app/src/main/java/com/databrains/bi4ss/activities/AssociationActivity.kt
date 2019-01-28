package com.databrains.bi4ss.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.databrains.bi4ss.R
import com.databrains.bi4ss.adapters.AssociationAdapter
import com.databrains.bi4ss.models.Response
import com.databrains.bi4ss.webServices.BI4SSWebService
import kotlinx.android.synthetic.main.activity_association.*
import kotlinx.android.synthetic.main.content_association.*
import retrofit2.Call
import retrofit2.Callback


class AssociationActivity : AppCompatActivity(), Callback<Response> {
    override fun onFailure(call: Call<Response>, t: Throwable) {
        Log.e("Error", t.localizedMessage)
    }

    override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
        val adapter = AssociationAdapter(listOf())
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        associationRecyclerView.layoutManager = manager
        associationRecyclerView.adapter = adapter
        associationProgressBar.visibility = View.GONE
        associationRecyclerView.visibility = View.VISIBLE
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
