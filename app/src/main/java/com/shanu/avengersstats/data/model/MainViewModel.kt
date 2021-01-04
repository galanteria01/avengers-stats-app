package com.shanu.avengersstats.data.model

import Hero
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanu.avengersstats.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {
    val myHeroData:MutableLiveData<Response<Hero>> = MutableLiveData()

    fun getHero(id:String){
        viewModelScope.launch{
            val response = repository.getDetails(id)
            myHeroData.value = response
        }
    }
}