package com.gamesidekicks.data

import com.gamesidekicks.data.api.IRemoteRepository
import com.gamesidekicks.data.domain.ILocalRepository
import com.gamesidekicks.data.domain.model.Villager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import com.gamesidekicks.data.utils.Result
import com.gamesidekicks.data.utils.Result.Success
import com.gamesidekicks.data.utils.Result.Error

/**
 * Default implementation of [Repository]. Single entry point.
 */
class Repository(
    private val remote: IRemoteRepository,
    private val local: ILocalRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IMainRepository {

    init {
        runBlocking {
            getVillagers(true) // TODO add a condition so the app doesnt always go to the backend
        }
    }

    override suspend fun getVillagers(forceUpdate: Boolean): Result<List<Villager>> {
        if (forceUpdate) {
            try {
                updateVillagersFromRemote()
            } catch (ex: Exception) {
                return Error(ex)
            }
        }
        return local.getVillagers()
    }

    override fun getVillagersStream(): Flow<Result<List<Villager>>> {
        return local.getVillagersStream()
    }

    override suspend fun getVillager(villagerId: String): Result<Villager>? {
        local.getVillager(villagerId)
        return null
    }
    
    override suspend fun saveVillager(villager: Villager) {
        local.saveVillager(villager)
    }

    private suspend fun updateVillagersFromRemote() {
        val remoteVillagers = remote.getVillagers()
        if (remoteVillagers is Success) {
            remoteVillagers.data.forEach {
                local.saveVillager(it.asDomainModel())
            }
        } else if (remoteVillagers is Error) {
            throw remoteVillagers.exception
        }
    }

}