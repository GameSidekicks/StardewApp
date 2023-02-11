/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gamesidekicks.data.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gamesidekicks.data.domain.model.Villager
import kotlinx.coroutines.flow.Flow

@Dao
interface IVillagersDao {

    /**
     * Observes list of villagers.
     *
     * @return all villagers.
     */
    @Query("SELECT * FROM Villagers")
    fun observeVillagers(): Flow<List<Villager>>

    /**
     * Observes a single villager.
     *
     * @param villagerId the villager id.
     * @return the villager with villagerId.
     */
    @Query("SELECT * FROM Villagers WHERE entryId = :villagerId")
    fun observeVillagerById(villagerId: String): Flow<Villager>

    /**
     * Select all villagers from the villagers table.
     *
     * @return all villagers.
     */
    @Query("SELECT * FROM Villagers")
    suspend fun getVillagers(): List<Villager>

    /**
     * Select a villager by id.
     *
     * @param villagerId the villager id.
     * @return the villager with villagerId.
     */
    @Query("SELECT * FROM Villagers WHERE entryId = :villagerId")
    suspend fun getVillagerById(villagerId: String): Villager?

    /**
     * Insert a villager in the database. If the villager already exists, replace it.
     *
     * @param villager the villager to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVillager(villager: Villager)

    /**
     * Update a Villager.
     *
     * @param villager villager to be updated
     * @return the number of villagers updated. This should always be 1.
     */
    @Update
    suspend fun updateVillager(villager: Villager): Int

    /**
     * Delete all Villagers.
     */
    @Query("DELETE FROM Villagers")
    suspend fun deleteVillagers()
}
