package com.example.cleancoding.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.cleancoding.data.networking.HttpClientManager
import com.example.cleancoding.data.networking.api.CharacterApi
import com.example.cleancoding.data.model.Character
import com.example.cleancoding.data.networking.createApi
import com.example.cleancoding.data.networking.datasource.CharacterDataSource
import kotlinx.coroutines.CoroutineScope

private class CharacterRepositoryImpl(
    private val api: CharacterApi
) : CharacterRepository {
    private val paginationConfig = PagedList.Config
        .Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()
    override fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<Character>> {
        return LivePagedListBuilder(
            CharacterDataSource.Factory(api, scope),
            paginationConfig
        ).build()
    }
}
interface CharacterRepository {
    fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<Character>>

    companion object {
        val instance: CharacterRepository by lazy {
            CharacterRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}