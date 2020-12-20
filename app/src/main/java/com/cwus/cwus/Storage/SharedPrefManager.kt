package com.yoof.yoof.Storage


import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }
/*

    val user: User
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getInt("calories", 0),
                sharedPreferences.getInt("eatAmount", 0),
            )
        }


    fun saveUser(user: User) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", user.id)
        editor.putString("email", user.email)
        editor.putString("username", user.username)
        editor.putString("password", user.password)
        editor.putInt("calories", user.calories)
        editor.putInt("eating_amount", user.eating_amount)
        editor.putBoolean("is_superuser", user.is_superuser)

        editor.apply()

    }
*/

    private var sharedPreferences : SharedPreferences  = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun saveAuthToken(token: String){
        val editor = sharedPreferences.edit()

        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String?{
        return sharedPreferences.getString(USER_TOKEN, null)
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"

        const val USER_TOKEN = "token"

        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}