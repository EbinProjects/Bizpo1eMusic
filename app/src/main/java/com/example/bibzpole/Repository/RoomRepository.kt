package com.example.bibzpole.Repository

import com.example.bibzpole.dataModel.LoginTable
import com.example.bibzpole.dataModel.ResultsItem
import com.example.bibzpole.database.SaveFavDao
import com.example.bibzpole.database.USerDao
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val saveFavDao: SaveFavDao,
    private val uSerDao: USerDao){
    fun getfavList()=saveFavDao.getAll()
    fun getUser(user : String)=uSerDao.getSelected(user)
    suspend fun insertReg( user: LoginTable) {
       uSerDao.insertAll(user)
    }
    suspend fun update( status: Int,id: Int) {
        uSerDao.update(status,id)
    }

    fun getAllUser(user: String) =uSerDao.getAll(user)
    fun getAllUser(user: String,pass : String) =uSerDao.getAll(user,pass)
    fun getLogonUser()=uSerDao.getLogin()
    suspend fun Add(data: ResultsItem) {
        return saveFavDao.insertAll(data)
    }

}