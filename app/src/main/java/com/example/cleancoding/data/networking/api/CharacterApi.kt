package com.example.cleancoding.data.networking.api

import com.example.cleancoding.data.model.Character
import com.example.cleancoding.data.networking.api.CharacterApi.Companion.GET_ALL_CHARACTER_PATH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET(GET_ALL_CHARACTER_PATH)
    suspend fun getAllCharacter(
        @Query("page") page: Int
    ): Response<List<Character>>

    companion object {
        const val GET_ALL_CHARACTER_PATH = "character/"
    }

}