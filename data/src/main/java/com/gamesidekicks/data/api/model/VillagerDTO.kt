package com.gamesidekicks.data.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class VillagerDTO (
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("birthday")
    val birthday: String = ""
)