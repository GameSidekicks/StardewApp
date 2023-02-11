package com.gamesidekicks.data.api

import com.gamesidekicks.data.api.model.VillagerDTO
import retrofit2.Response
import retrofit2.http.GET

interface IService {

    @GET("dTk7Zt")
    suspend fun getMockVillagers(
    ) : Response<List<VillagerDTO>>

/*    @GET("/3/discover/movie?api_key=1596a3fe1e067ec582245934efecd9d2")
    suspend fun getDiscoverMovies(
        @Query("page") page: Int,
        @Query("with_genres") genres: Int?
    ) : Response<PagerDTO>

    @GET("/3/search/movie?api_key=1596a3fe1e067ec582245934efecd9d2&include_adult=false")
    suspend fun getSearchMovies(
        @Query("page") page: Int,
        @Query("query") query: String?,
        @Query("with_genres") genres: Int?,
        @Query("sort_by") string: String?
    ) : Response<PagerDTO>

    @GET("/3/movie/{id}?api_key=1596a3fe1e067ec582245934efecd9d2") // TODO mover api key
    suspend fun getMovie(@Path("id") id: Int) : Response<MovieDTO>

    @GET("/3/movie/{id}/credits?api_key=1596a3fe1e067ec582245934efecd9d2")
    suspend fun getMovieCredits() : Response<List<GenreDTO>>

    @GET("/3/search/person?api_key=1596a3fe1e067ec582245934efecd9d2")
    suspend fun getSearchPerson(@Query("query") query: String?) : Response<List<GenreDTO>>

    @GET("/3/genre/movie/list?api_key=1596a3fe1e067ec582245934efecd9d2")
    suspend fun getGenres() : Response<List<GenreDTO>>*/
}