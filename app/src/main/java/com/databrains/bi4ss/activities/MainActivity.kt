package com.databrains.bi4ss.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.databrains.bi4ss.BI4SSWebService
import com.databrains.bi4ss.R
import com.databrains.bi4ss.models.Response
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), Callback<Response> {

    override fun onFailure(call: Call<Response>, t: Throwable) {
        Log.e("Error", t.message)
    }

    override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

    }

    val webService = BI4SSWebService.retrofit.create(BI4SSWebService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connect_button.setOnClickListener {
            val studentId = student_id_edit_text.text.toString()
            val id = levels_radio_group.checkedRadioButtonId
            if (studentId.isNotEmpty()) {
                webService.connect(studentId, getCurrentYearFromRadio(id), getLevelFromRadio(id)).enqueue(this)
            }
        }

        profile_button.setOnClickListener {

        }
    }

    private fun getCurrentYearFromRadio(id: Int): String {
        return when (id) {
            R.id.l1_radio_button, R.id.m1_radio_button -> "1"
            R.id.l2_radio_button, R.id.m2_radio_button -> "2"
            R.id.l3_radio_button -> "3"
            else -> ""
        }
    }

    private fun getLevelFromRadio(id: Int): String {
        return when (id) {
            R.id.l1_radio_button, R.id.l2_radio_button, R.id.l3_radio_button -> "MIAS"
            R.id.m1_radio_button, R.id.m2_radio_button -> "MGI"
            else -> ""
        }
    }
}
