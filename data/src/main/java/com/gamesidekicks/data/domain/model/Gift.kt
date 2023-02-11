package com.gamesidekicks.data.domain.model

import androidx.compose.ui.graphics.painter.Painter

data class Gift(
    val icon: Painter,
    val name: String,
    val itemCollection: ItemCollection
)
