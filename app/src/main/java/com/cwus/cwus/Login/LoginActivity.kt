package com.cwus.cwus.Login

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progress.visibility = View.INVISIBLE
        login.setOnClickListener{
            val email = username.text.toString().trim()
            if (email.isEmpty()){
                return@setOnClickListener
            }

            val password = password.text.toString().trim()
            if (password.isEmpty()){
                return@setOnClickListener
            }

            progress.visibility = View.VISIBLE
            Common.retrofitService.loginUser(email, password).enqueue(object: Callback<TokenResponse> {
                override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {
                    progress.visibility = View.INVISIBLE

                    Log.d("MEVENT AUTH", "body: ${response.body()} | code: ${response.code()}")

                    if(response.isSuccessful && response.body() != null){
                        SharedPrefManager.getInstance(applicationContext).saveAuthToken(response.body()!!.token)

                        val intent = Intent(applicationContext, ChatRoomActivity::class.java)

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