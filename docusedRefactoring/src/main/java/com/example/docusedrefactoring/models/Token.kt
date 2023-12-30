
package com.example.docusedrefactoring.models

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token_type")
    val tokenType: String = "",
    @SerializedName("expires_in")
    val expiresIn: Int = 0,
    @SerializedName("access_token")
    val accessToken: String = ""
)
