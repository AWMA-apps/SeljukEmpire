package com.awma.seljukempire.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.awma.seljukempire.data.api.CountryRepository
import com.awma.seljukempire.data.model.Characters
import com.awma.seljukempire.data.model.Events
import com.awma.seljukempire.data.repository.CountryRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repository: CountryRepository) : ViewModel() {
    private val _events = MutableLiveData< List<Events?>>()
    val events: LiveData<List<Events?>> get() = _events

    suspend fun loadEvents() {
        _events.value = repository.loadEvents()
    }

    private val _characters = MutableLiveData< List<Characters?>>()
    val characters: LiveData<List<Characters?>> get() = _characters

    suspend fun loadCharacters() {
        _characters.value = repository.loadCharacters()
        //Log.d("AppViewModel", "loadCharacters: ${_characters.value}")
    }
}