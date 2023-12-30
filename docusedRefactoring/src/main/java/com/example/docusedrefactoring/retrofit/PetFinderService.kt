
package com.example.docusedrefactoring.retrofit

import com.example.docusedrefactoring.models.*
import com.example.docusedrefactoring.models.AnimalResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface PetFinderService {

  @POST("oauth2/token")
  @FormUrlEncoded
  fun getToken(
      @Field("grant_type") grantType: String = "client_credentials",
      @Field("client_id") clientId: String,
      @Field("client_secret") clientSecret: String): Call<Token>

  @GET("animals")
  suspend fun getAnimals(
      @Header("Authorization")  accessToken: String,
      @Query("limit") limit: Int = 20,
      @Query("location") location: String? = null
  ) : Response<AnimalResult>
}