package com.databrains.bi4ss.activities

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.databrains.bi4ss.BI4SSWebService
import com.databrains.bi4ss.R
import com.databrains.bi4ss.models.Prediction
import kotlinx.android.synthetic.main.content_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity(), Callback<Prediction> {

    override fun onResponse(call: Call<Prediction>, response: Response<Prediction>) {
        val prediction = response.body()
        Log.d("TaG" , prediction.toString())
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Check Result")
        builder.setMessage("You are a ${if (prediction?.prediction == 0) "Failed" else "Successful"} person")
        builder.show()
    }

    override fun onFailure(call: Call<Prediction>, t: Throwable) {
        Log.d("Error", t.message)
    }

    val webService = BI4SSWebService.retrofit.create(BI4SSWebService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        check_button.setOnClickListener {
            val bacAverage = bac_average_edit_text.text.toString()
            val wilaya = bac_wilaya_edit_text.text.toString()
            val age = age_edit_text.text.toString()
            if (bacAverage.isNotEmpty() && wilaya.isNotEmpty() && age.isNotEmpty()) {
                Log.e("TaG" , "Before Enquee")
                webService.profileCheckSuccess(
                        if (profile_gender_radio_group.checkedRadioButtonId == R.id.male_radio_button) "M"
                        else "F",
                        if (nationality_check.isChecked) 1 else 0,
                        wilaya.toInt(),
                        bacAverage.toDouble(),
                        age.toInt()).enqueue(this)
                Log.e("TaG" , "After Enquee")
            } else {
                Toast.makeText(this, "Please Fill The Fields", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
