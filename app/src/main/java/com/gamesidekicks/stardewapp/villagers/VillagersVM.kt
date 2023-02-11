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

package com.gamesidekicks.stardewapp.villagers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamesidekicks.data.IMainRepository
import com.gamesidekicks.data.domain.model.Villager
import com.gamesidekicks.stardewapp.utils.Async
import com.gamesidekicks.data.utils.Result
import com.gamesidekicks.data.utils.Result.Success

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


data class VillagersUiState(
    val items: List<Villager> = emptyList(),
    val isEmpty: Boolean = false,
    val isLoading: Boolean = false
)

@HiltViewModel
class VillagersVM @Inject constructor(
    private val villagersRepository: IMainRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Used to save the current filtering in SavedStateHandle.
    private val _savedFilterType = savedStateHandle.getStateFlow("SAVED_STATE_KEY", "ALL_VILLAGERS")

    private val _isLoading = MutableStateFlow(false)
    private val _villagersAsync =
        combine(villagersRepository.getVillagersStream(), _savedFilterType) { villagers, _ ->
        filterVillagers(villagers/*, type*/)
    }
        .map { Async.Success(it) }
        .onStart<Async<List<Villager>>> { emit(Async.Loading) }

    val uiState: StateFlow<VillagersUiState> = combine(_isLoading, _villagersAsync)
    { isLoading, villagersAsync ->
        when (villagersAsync) {
            Async.Loading -> {
                VillagersUiState(isLoading = true)
            }
            is Async.Success -> {
                VillagersUiState(
                    items = villagersAsync.data,
                    isLoading = isLoading,
                )
            }
        }
    }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = VillagersUiState(isLoading = true)
        )

    private fun filterVillagers(
        villagersResult: Result<List<Villager>>,
        //filteringType: TasksFilterType
    ): List<Villager> = if (villagersResult is Success) {
        filterItems(villagersResult.data/*, filteringType*/)
    } else {
        //showSnackbarMessage(R.string.loading_tasks_error)
        emptyList()
    }

    private fun filterItems(villagers: List<Villager>/*, filteringType: TasksFilterType*/): List<Villager> {
        val villagersToShow = ArrayList<Villager>()
        // We filter the tasks based on the requestType
        for (villager in villagers) {
/*            when (filteringType) {
                ALL_TASKS -> tasksToShow.add(task)
                ACTIVE_TASKS -> if (task.isActive) {
                    tasksToShow.add(task)
                }
                COMPLETED_TASKS -> if (task.isCompleted) {
                    tasksToShow.add(task)
                }
            }*/

            villagersToShow.add(villager)
        }
        return villagersToShow
    }

}
