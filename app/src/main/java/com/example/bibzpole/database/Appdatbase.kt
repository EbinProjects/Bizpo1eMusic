package com.example.bibzpole.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bibzpole.dataModel.LoginTable
import com.example.bibzpole.dataModel.ResultsItem
import com.example.bibzpole.fileUtils.Constants



@Database(
    entities = [
        ResultsItem::class,
        LoginTable::class,
  ], version = 1, exportSchema = true
)

//auto bit map conversion
abstract class Appdatbase : RoomDatabase() {

    abstract fun savefavDao(): SaveFavDao
    abstract fun login(): USerDao


    companion object {
        @Volatile
        private var instance: Appdatbase? = null

        fun getInstance(context: Context): Appdatbase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): Appdatbase {
            return Room.databaseBuilder(
                context,
                Appdatbase::class.java,
                Constants.DATABASE
            )
                .allowMainThreadQueries().build()
        }
    }

}