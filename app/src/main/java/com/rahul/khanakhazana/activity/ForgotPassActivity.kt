package com.rahul.khanakhazana.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.rahul.khanakhazana.R

class ForgotPassActivity : AppCompatActivity() {

    lateinit var etMobile:EditText
    lateinit var etEmail:EditText
    lateinit var btnNext:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass)

        etMobile = findViewById(R.id.etMobile)
        etEmail = findViewById(R.id.etEmail)
        btnNext = findViewById(R.id.btnNext)

        //Here Lambda Representation Method of click listener/events listeners is used instead of Longer method
        btnNext.setOnClickListener{

            val intent = Intent(this@ForgotPassActivity,
                SetNewPassActivity::class.java)
            startActivity(intent)
        }


    }
}
