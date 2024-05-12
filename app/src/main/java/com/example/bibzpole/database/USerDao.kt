package com.example.bibzpole.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bibzpole.dataModel.LoginTable
import kotlinx.coroutines.flow.Flow


@Dao
interface USerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: LoginTable)

    @Delete
    fun delete(items: LoginTable)

    @Query("DELETE  FROM LoginTable")
    suspend  fun clear()

    @Query("UPDATE LoginTable SET loginStatus = :status WHERE Id = :id")
    suspend  fun update(status : Int, id : Int)

    @Query("SELECT * FROM LoginTable WHERE user =:user")
    fun getAll(user: String): List<LoginTable>

    @Query("SELECT * FROM LoginTable WHERE user =:user AND password =:pass")
    fun getAll(user: String,pass : String): List<LoginTable>

    @Query("SELECT * FROM LoginTable WHERE loginStatus = 1")
    fun getLogin(): Flow<List<LoginTable>>

    @Query("SELECT * FROM LoginTable  WHERE user = :user ")
    fun getSelected(user : String): List<LoginTable>
}