package com.yoof.yoof.Model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("token")
    var token: String
)