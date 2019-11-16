package com.programacionymas.io

import com.programacionymas.model.Player
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ApiService {

    @GET("players")
    fun getPlayers(): Call<ArrayList<Player>>

    @GET("players/{player}")
    @Headers("Accept: application/json")
    fun getPlayer(@Path("player") playerId: Int): Call<Player>

    @POST("players")
    @Headers("Accept: application/json")
    fun postPlayer(
        @Header("Authorization") authHeader: String,
        @Query("level") level: Int,
        @Query("name") name: String,
        @Query("intelligence") intelligence: Double,
        @Query("agility") agility: Double,
        @Query("strength") strength: Double
    ): Call<Void>

    @POST("players")
    @Headers("Accept: application/json")
    fun postPlayer(
        @Header("Authorization") authHeader: String,
        @Body player: Player
    ): Call<Void>

    @POST("players")
    @Headers("Accept: application/json")
    fun postPlayers(
        @Header("Authorization") authHeader: String,
        @Body players: List<Player>
    ): Call<Void>

    companion object Factory {
        private const val BASE_URL = "https://en0kc432zq3kl.x.pipedream.net/api/"

        fun create(): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}