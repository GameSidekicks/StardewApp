package com.gamesidekicks.data

import com.gamesidekicks.data.api.model.VillagerDTO
import com.gamesidekicks.data.domain.model.Villager


fun VillagerDTO.asDomainModel() = Villager(id, image, name, birthday)

/*fun VillagerDTO.asDomainModel() = with(::Villager) {
    val propertiesByName = VillagerDTO::class.memberProperties.associateBy { it.name }
    callBy(parameters.associateWith { parameter ->
        when (parameter.name) {
            Villager::id.name -> id
            Villager::image.name -> image
            Villager::name.name -> name
            Villager::birthday.name -> birthday
            else -> propertiesByName[parameter.name]?.get(this@asDomainModel)
        }
    })
}*/

