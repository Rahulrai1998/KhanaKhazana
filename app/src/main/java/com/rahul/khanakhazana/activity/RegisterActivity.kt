package com.rahul.khanakhazana.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rahul.khanakhazana.R

class RegisterActivity : AppCompatActivity() {


    lateinit var registerToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerToolbar = findViewById(R.id.registerToolbar)

        setUpToolbar()



    }

    fun setUpToolbar(){
        setSupportActionBar(registerToolbar)
        supportActionBar?.title = "Register Yourself"

        //for home button (back button)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //only to click home button(back button) and to get back to previous screen , following function is enough after toolbar and homebutton enabled .....
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}
