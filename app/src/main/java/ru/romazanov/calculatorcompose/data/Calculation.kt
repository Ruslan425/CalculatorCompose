package ru.romazanov.calculatorcompose.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Calculation(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val calculation: String,
    @ColumnInfo val data: String
)

