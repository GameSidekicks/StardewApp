package com.gamesidekicks.data.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "villagers")
data class Villager (
    @PrimaryKey @ColumnInfo(name = "entryId")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "image")
    val image: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "birthday")
    val birthday: String = ""
)

/*
Villager(
  id = "1",
  image = "stardewvalleywiki.com/mediawiki/images/thumb/0/04/Alex.png/80px-Alex.png",
  name = "Alex",
  birthday = "Summer 13"
)
*/
