package com.example.movies.data


import com.example.movies.data.remote.CheckConnection
import com.example.movies.data.remote.FilmsApi
import com.example.movies.data.remote.ResultDTO
import com.example.movies.domain.di.AppModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class FilmsRepository @Inject constructor(private val filmsApi: FilmsApi) {
    suspend fun getFilms(): Flow<CheckConnection<List<ResultDTO>>> {
        return flow {
            val filmsFromApi = try {
                filmsApi.getFilmsByApi(AppModule.API_KEY, 1)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(CheckConnection.Error(message = "loading films error"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(CheckConnection.Error(message = "Connection error"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(CheckConnection.Error(message = "Fatal error"))
                return@flow
            }
            emit(CheckConnection.Success(filmsFromApi.results))
        }
    }
}