package com.rahul.khanakhazana.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.rahul.khanakhazana.R

class LoginActivity : AppCompatActivity() /*, View.OnClickListener*/ {

    lateinit var etMobile:EditText  //Variables Declaration to connect views in XML to Kotlin file
    lateinit var etPassword:EditText
    lateinit var btnLogin:Button
    lateinit var txtForgot:TextView
    lateinit var txtRegister:TextView
    val name = "Rahul"
    val pass = "123456789"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)//R file contains the Ids of resource files in res directory
        //setContentView method is used to connect the layout/XML file to the Kotlin file by adding the XML file'S name in it....

        etMobile = findViewById(R.id.etMobile) //By using findViewById function we connect views Id to the declared variables in Kt file
        etPassword = findViewById(R.id.etPassword) //We do such connections in order to save data received corresponding the views declared
        btnLogin = findViewById(R.id.btnLogin)
        txtForgot = findViewById(R.id.txtForgot)
        txtRegister = findViewById(R.id.txtRegister)

        //to make the views clickable or functional or eventful we use eventlisteners to the views such as Onclicklisteners etc .
        //all the views such as TextView , EditText are declared under View class
        //OnClickListeners is an Interface of View class and Onclick is a method of this interface

        btnLogin.setOnClickListener{

            Toast.makeText(this@LoginActivity , "This the Lamda representation" , Toast.LENGTH_LONG).show() /*this is the Lambda Representation
             to make a toast easily*/
            val intent = Intent(this@LoginActivity , MainActivity::class.java)
            startActivity(intent)
        } //to make login button clickable

        txtForgot.setOnClickListener {

            val intent = Intent(this@LoginActivity , ForgotPassActivity::class.java)/*
            to create a bridge between two activities we create Intent object and pass it to startActivity method as parameter*/
            startActivity(intent)
        }
        txtRegister.setOnClickListener {

            val intent = Intent(this@LoginActivity , RegisterActivity::class.java)
            startActivity(intent)
        }


    }


    /*override fun onClick(v: View?) { //its the framework were all eventlisters activities take place on clicking the views
        Toast.makeText(this@LoginActivity,"Wait a minute",Toast.LENGTH_SHORT).show()//show function is used to display the Toast on clicking
    }*///this the longer method to make a  events like toasts etc

}
