package com.example.bibzpole.Repository

import com.example.bibzpole.Interfaces.MusicApi
import com.example.bibzpole.dataModel.ResponseLists
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val musicApi : MusicApi) {

    suspend fun search(
        data: String,
    ) : Response<ResponseLists?>? {
        return musicApi.search(data)
    }
}