
package com.example.docusedrefactoring.retrofit

import com.example.docusedrefactoring.MainActivity
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthorizationInterceptor(private val mainActivity: MainActivity) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    var mainResponse = chain.proceed(chain.request())
    val mainRequest = chain.request()

    // if response code is 401 or 403, 'mainRequest' has encountered authentication error
    if ((mainResponse.code() == 401 || mainResponse.code() == 403) && !mainResponse.request().url().url().toString().contains("oauth2/token")) {
      // request to login API to get fresh token
      // synchronously calling login API
      mainActivity.petFinderService?.let { petFinderService ->
        val tokenRequest = petFinderService.getToken(clientId = mainActivity.apiKey, clientSecret = mainActivity.apiSecret)
        val tokenResponse = tokenRequest.execute()

        if (tokenResponse.isSuccessful) {
          // login request succeeded, new token generated
          // save the new token
          // retry the 'mainRequest' which encountered an authentication error
          // add new token into 'mainRequest' header and request again
          tokenResponse.body()?.let {
            mainActivity.token = it
            val builder = mainRequest.newBuilder().header("Authorization", "Bearer " + it.accessToken)
                .method(mainRequest.method(), mainRequest.body())
            mainResponse.close()
            mainResponse = chain.proceed(builder.build())
          }
        }
      }
    }

    return mainResponse
  }

}
