package com.databrains.bi4ss.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.databrains.bi4ss.R
import com.databrains.bi4ss.models.Response
import com.databrains.bi4ss.webServices.BI4SSWebService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), Callback<Response> {

    private val webService = BI4SSWebService.retrofit.create(BI4SSWebService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        connect_button.setOnClickListener {
            val studentId = student_id_edit_text.text.toString()
            val id = levels_radio_group.checkedRadioButtonId
            if (studentId.isNotEmpty()) {
                webService.connect(studentId, getCurrentYearFromRadio(id), getLevelFromRadio(id)).enqueue(this)
            }

        }

        profile_button.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
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

    override fun onFailure(call: Call<Response>, t: Throwable) {
        Log.e("Error", t.message)
    }

    override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
        val responseTwo = response.body()
        if (responseTwo?.year != null) {
            val intent = Intent(this, GeneralActivity::class.java)
            intent.putExtra(GeneralActivity.keyScholarYear, responseTwo.year)
            intent.putExtra(GeneralActivity.keyCurrentYear, getCurrentYearFromRadio(levels_radio_group.checkedRadioButtonId))
            intent.putExtra(GeneralActivity.keyLevel, getLevelFromRadio(levels_radio_group.checkedRadioButtonId))
            startActivity(intent)
        } else {
            Toast.makeText(this, "You are not in our university.", Toast.LENGTH_LONG).show()
        }
    }
}
