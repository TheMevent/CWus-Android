package com.cwus.cwus.Register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cwus.cwus.ChatRoom.ChatRoomActivity
import com.cwus.cwus.R
import com.yoof.yoof.Common.Common
import com.yoof.yoof.Model.TokenResponse
import com.yoof.yoof.Storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.progress
import kotlinx.android.synthetic.main.activity_register.username
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        progress.visibility = View.GONE
        register.setOnClickListener{
            val email = email.text.toString().trim()
            if (email.isEmpty()){
                return@setOnClickListener
            }

            val username = username.text.toString().trim()
            if (username.isEmpty()){
                return@setOnClickListener
            }

            val password = password.text.toString().trim()
            if (password.isEmpty()){
                return@setOnClickListener
            }

            progress.visibility = View.VISIBLE
            Common.retrofitService.regUser(email, username, password).enqueue(object: Callback<TokenResponse>{
                override fun onResponse(
                    call: Call<TokenResponse>,
                    response: Response<TokenResponse>
                ) {
                    progress.visibility = View.INVISIBLE

                    if(response.isSuccessful && response.body() != null){
                        SharedPrefManager.getInstance(applicationContext).saveAuthToken(response.body()!!.token)

                        val intent = Intent(applicationContext, ChatRoomActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext, response.body()?.toString(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                    progress.visibility = View.INVISIBLE

                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }

    }
}