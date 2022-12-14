package com.example.movieapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.models.ShowDetailsResponse
import com.example.movieapp.repository.ShowDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel
    @Inject
    constructor(
        private val showDetailsRepository: ShowDetailsRepository
    ): ViewModel(){

    fun detailsByID(id: Int): MutableLiveData<ShowDetailsResponse>{
        val details = MutableLiveData<ShowDetailsResponse>()
        viewModelScope.launch(Dispatchers.IO) {
            val data = showDetailsRepository.getDetailsByID(id)
            withContext(Dispatchers.Main){
                details.value = data!!
            }
        }
        return details
    }

}