package com.gamesidekicks.data.api

import com.gamesidekicks.data.api.model.VillagerDTO
import com.gamesidekicks.data.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import com.gamesidekicks.data.utils.Result.Success

object RemoteRepository: IRemoteRepository {

    private val service = Service.getRetrofit()
    private val observableVillagers = MutableStateFlow(runBlocking { getVillagers() })

    override suspend fun getVillagers(): Result<List<VillagerDTO>> {
        var villagers: List<VillagerDTO> = listOf()
        val response = service.getMockVillagers()
        if(response.isSuccessful) {
            response.body()?.let {
                villagers = it
            }
        }
        return Success(villagers)
    }

    override fun getVillagersStream(): Flow<Result<List<VillagerDTO>>> {
        return observableVillagers
    }

    override suspend fun getVillager(villagerId: String): Result<VillagerDTO>? {
        // Only Local
        return null
    }

    override suspend fun saveVillager(villager: VillagerDTO) {
        // Only Local
    }
}
