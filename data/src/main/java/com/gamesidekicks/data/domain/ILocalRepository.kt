package com.gamesidekicks.data.domain

import com.gamesidekicks.data.domain.model.Villager
import kotlinx.coroutines.flow.Flow
import com.gamesidekicks.data.utils.Result


interface ILocalRepository {

    suspend fun getVillagers(forceUpdate: Boolean = false): Result<List<Villager>>

    fun getVillagersStream(): Flow<Result<List<Villager>>>

    suspend fun getVillager(villagerId: String): Result<Villager>?

    suspend fun saveVillager(villager: Villager)
}