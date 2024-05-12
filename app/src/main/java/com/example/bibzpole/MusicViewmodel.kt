package com.example.bibzpole

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bibzpole.Repository.ApiRepository
import com.example.bibzpole.Repository.RoomRepository
import com.example.bibzpole.dataModel.LoginTable
import com.example.bibzpole.dataModel.ResultsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MusicViewmodel  @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val apiRepository: ApiRepository,
    private val roomRepository: RoomRepository,
    @SuppressLint("StaticFieldLeak") private val context: Context
) : ViewModel(){
private val TAG ="MusicViewmodel"
    private val _musics = MutableStateFlow<List<ResultsItem>>(emptyList())
    val musics = _musics.asStateFlow()
    private val _musicsTrack = MutableStateFlow<ResultsItem>(ResultsItem())
    val musicsTrack = _musicsTrack.asStateFlow()
    private val _progress = MutableStateFlow<Boolean>(false)
    val progress = _progress.asStateFlow()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "exceptionHandler: ${throwable.localizedMessage?.toString()}", )
       viewModelScope.launch {
           withContext(Dispatchers.Main){
             Toast.makeText(context,"No Internet!",Toast.LENGTH_LONG).show()
           }
       }

    }

init {
    searchSongs("mohanlal")
}

    fun searchSongs (serach :String){
        _progress.value = true
        viewModelScope.launch(Dispatchers.IO+exceptionHandler) {
          val resposList = apiRepository.search(serach)
            withContext(Dispatchers.Main){
                if (resposList != null) {
                    if (resposList.isSuccessful){
                        _musics.value = resposList.body()?.results as List<ResultsItem>
                        _progress.value = false
                    }else{
                        _progress.value = false
                    }
                }else{
                    _progress.value = false
                }

            }
        }
    }
    fun searchSongsbyrackId (serach :String){
        _progress.value = true
        viewModelScope.launch(Dispatchers.IO+exceptionHandler) {
            val resposList = apiRepository.search(serach)
            withContext(Dispatchers.Main){
                if (resposList != null) {
                    if (resposList.isSuccessful){
                        val data = resposList.body()?.results as List<ResultsItem>
                        if (data.isNotEmpty()) {
                            _musicsTrack.value = data[0]
                        }
                        _progress.value = false
                    }else{
                        _progress.value = false
                    }
                }else{
                    _progress.value = false
                }

            }
        }
    }

    val getFavLists = roomRepository.getfavList()
    val getLogUsers = roomRepository.getLogonUser()
    fun getSelectedUser(user : String) = roomRepository.getUser(user)

    fun insertFav(item : ResultsItem){

    }


    fun resgister(user :String,pass :String,name :String,function : (Boolean)  -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val users = roomRepository.getAllUser(user)
            withContext(Dispatchers.Main){
                if (users.isNotEmpty()){
                    login(1,users[0].Id)
                    function(false)
                }else{
                    val login = LoginTable(name,user,pass,0)
                    roomRepository.insertReg(login)
                    function(true)
                }


            }
        }

    }

    suspend fun login(status : Int,id : Int){
        roomRepository.update(status,id)
    }

    fun loginsuccess(user: String, pass: String, function: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val users = roomRepository.getAllUser(user,pass)
            withContext(Dispatchers.Main){
                if (users.isNotEmpty()){
                    login(1,users[0].Id)
                    sharedPreferences.edit().apply {
                        putBoolean("Login",true).apply()
                    }
                    function(true)
                }else{
                    function(false)
                }


            }
        }
    }

    fun logout(id: Int) {
  viewModelScope.launch {
      login(0,id)
      sharedPreferences.edit().apply {
          putBoolean("Login",false).apply()
      }
  }
    }

    fun clickStart() {
        sharedPreferences.edit().apply {
            putBoolean("Start",true).apply()
        }
    }

    fun addFav(data: ResultsItem, user: Int, function: (Boolean) -> Unit) {
      viewModelScope.launch {
          data.user =user
          roomRepository.Add(data)
          function(true)
      }
    }
}