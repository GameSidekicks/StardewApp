package com.gamesidekicks.data.api

import com.gamesidekicks.data.api.model.VillagerDTO
import kotlinx.coroutines.flow.Flow
import com.gamesidekicks.data.utils.Result


interface IRemoteRepository {

    suspend fun getVillagers(): Result<List<VillagerDTO>>

    fun getVillagersStream(): Flow<Result<List<VillagerDTO>>>

    suspend fun getVillager(villagerId: String): Result<VillagerDTO>?

    suspend fun saveVillager(villager: VillagerDTO)
}