package com.example.movies.data.remote


import com.example.movies.data.local.FilmsDB
import com.example.movies.data.local.toFilmsEntity
import com.example.movies.data.local.toLocalFilms
import com.example.movies.domain.FilmItem
import com.example.movies.domain.di.AppModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class FilmsRepository @Inject constructor(
    private val filmsApi: FilmsApi,
    private val filmsDB: FilmsDB
) {
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
            val filmsEntities = filmsFromApi.results.let {
                it.map { filmDTO ->
                    filmDTO.toFilmsEntity()
                }
            }

            emit(CheckConnection.Success(filmsFromApi.results))
            filmsDB.filmsDAO.insertFilms(filmsEntities)
        }
    }

    suspend fun getLocalFilm(id: Int): Flow<CheckConnection<FilmItem>> {
        return flow {
            val filmsDB = filmsDB.filmsDAO.getFilmsById(id)
            emit(CheckConnection.Success(filmsDB.toLocalFilms()))
            return@flow

        }
    }
}