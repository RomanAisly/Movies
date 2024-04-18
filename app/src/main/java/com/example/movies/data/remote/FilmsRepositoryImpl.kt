package com.example.movies.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class FilmsRepositoryImpl(
    private val api: MovieApi
) : FilmsRepository {
    override suspend fun getFilmsByApi(): Flow<CheckConnection<List<ResultDTO>>> {
        return flow {
            val filmsFromApi = try {
                api.getFilmsByApi(MovieApi.API_KEY, 1)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(CheckConnection.Fail(message = "Loading has failed"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(CheckConnection.Fail(message = "Loading has failed"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(CheckConnection.Fail(message = "Loading has failed"))
                return@flow
            }
            emit(CheckConnection.Success(filmsFromApi.results))
        }
    }
}