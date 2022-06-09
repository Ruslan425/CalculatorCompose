package ru.romazanov.calculatorcompose.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "calculation")
data class CalculationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val calculation: String,
    @ColumnInfo val date: String
)

