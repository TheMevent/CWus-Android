package com.cwus.cwus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cwus.cwus.Login.LoginActivity
import com.cwus.cwus.Register.RegisterActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.isEnabled = true
        login.setOnClickListener {
            val etc = Intent(this, LoginActivity::class.java)
            startActivity(etc)

            login.isEnabled = false
            login.postDelayed({ login.isEnabled = true }, 500)
        }

        register.isEnabled = true
        register.setOnClickListener {
            val etc = Intent(this, RegisterActivity::class.java)
            startActivity(etc)

            register.isEnabled = false
            register.postDelayed({ register.isEnabled = true }, 500)
        }
    }
}