package com.example.bibzpole.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bibzpole.dataModel.ResultsItem
import kotlinx.coroutines.flow.Flow


@Dao
interface SaveFavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: ResultsItem)
    @Delete
    fun delete(items: ResultsItem)

    @Query("DELETE  FROM ResultsItem")
    suspend  fun clear()

    @Query("SELECT * FROM ResultsItem ")
    fun getAll(): Flow<List<ResultsItem>>
}
