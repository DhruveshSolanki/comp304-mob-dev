package com.dhruvesh.exercise1.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo("city")
    var cityName: String

) {}