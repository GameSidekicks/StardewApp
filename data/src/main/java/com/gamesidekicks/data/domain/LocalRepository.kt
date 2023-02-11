package com.gamesidekicks.data.domain

import com.gamesidekicks.data.IMainRepository
import com.gamesidekicks.data.domain.model.Villager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import com.gamesidekicks.data.utils.Result
import com.gamesidekicks.data.utils.Result.Error
import com.gamesidekicks.data.utils.Result.Success
import kotlinx.coroutines.flow.map

class LocalRepository internal constructor(
    private val villagersDao: IVillagersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ILocalRepository {

    override suspend fun getVillagers(forceUpdate: Boolean): Result<List<Villager>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Success(villagersDao.getVillagers())
            } catch (e: Exception) {
                Error(e)
            }
        }

    override fun getVillagersStream(): Flow<Result<List<Villager>>> {
        return villagersDao.observeVillagers().map {
            Success(it)
        }
    }

    override suspend fun getVillager(villagerId: String): Result<Villager> =
        withContext(ioDispatcher) {
            try {
                val villager = villagersDao.getVillagerById(villagerId)
                if (villager != null) {
                    return@withContext Success(villager)
                } else {
                    return@withContext Error(Exception("Villager not found!"))
                }
            } catch (e: Exception) {
                return@withContext Error(e)
            }
        }

    override suspend fun saveVillager(villager: Villager) = withContext(ioDispatcher) {
        villagersDao.insertVillager(villager)
    }

    // Private functions ---------------------------------------------------------------------------

    private fun deleteAllVillagers() {

    }

}