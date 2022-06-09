package ru.romazanov.calculatorcompose.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CalculationDao {

    @Query("SELECT * FROM calculation")
    fun getAll(): List<CalculationEntity>

    @Insert
    suspend fun addCalculation(calculation: CalculationEntity)

    @Query("DELETE FROM calculation")
    suspend fun deleteAll()


}


