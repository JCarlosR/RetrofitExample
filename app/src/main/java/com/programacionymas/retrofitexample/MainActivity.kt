package com.programacionymas.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.programacionymas.io.ApiService
import com.programacionymas.model.Player
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val player = Player(7, "Juan", 17.77, 25.55, 12.24)

        btnPostPlayerQuery.setOnClickListener {
            postPlayerUsingQuery(player)
        }

        btnPostPlayerBody.setOnClickListener {
            postPlayerUsingBody(player)
        }

        val player2 = Player(17, "Ramos", 7.53, 15.12, 24.12)
        val players = listOf(player, player2)

        btnPostPlayersBody.setOnClickListener {
            postPlayersUsingBody(players)
        }
    }

    private fun postPlayerUsingQuery(player: Player) {
        val call = apiService.postPlayer(
            "jwtWouldBeHere",
            player.level,
            player.name,
            player.intelligence,
            player.agility,
            player.strength
        )

        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.localizedMessage?.let {
                    Log.d(localClassName, it)
                }
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i(localClassName, response.body().toString())
            }
        })
    }

    private fun postPlayerUsingBody(player: Player) {
        val call = apiService.postPlayer(
            "jwtWouldBeHere",
            player
        )

        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.localizedMessage?.let {
                    Log.d(localClassName, it)
                }
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i(localClassName, response.body().toString())
            }
        })
    }

    private fun postPlayersUsingBody(players: List<Player>) {
        val call = apiService.postPlayers(
            "jwtWouldBeHere",
            players
        )

        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.localizedMessage?.let {
                    Log.d(localClassName, it)
                }
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i(localClassName, response.body().toString())
            }
        })
    }
}
