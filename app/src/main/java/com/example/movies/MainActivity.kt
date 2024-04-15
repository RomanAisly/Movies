package com.example.movies

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movies.data.remote.MovieApi
import com.example.movies.data.remote.ResponseDTO
import com.example.movies.domain.navigation.NavGraph
import com.example.movies.ui.theme.MoviesTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                NavGraph()
                val api = MovieApi.create().getFilms(MovieApi.API_KEY, 1)
                api.enqueue(object : Callback<List<ResponseDTO>> {
                    override fun onResponse(
                        call: Call<List<ResponseDTO>>,
                        response: Response<List<ResponseDTO>>
                    ) {

                    }

                    override fun onFailure(call: Call<List<ResponseDTO>>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            "Connection has failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            }

        }
    }
}