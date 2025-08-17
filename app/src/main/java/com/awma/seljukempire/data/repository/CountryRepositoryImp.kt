package com.awma.seljukempire.data.repository

import android.util.Log
import com.awma.seljukempire.data.api.CountryRepository
import com.awma.seljukempire.data.api.FirebaseApi
import com.awma.seljukempire.data.model.Characters
import com.awma.seljukempire.data.model.Events
import javax.inject.Inject

class CountryRepositoryImp @Inject constructor(private val firebaseApi: FirebaseApi) : CountryRepository{
    override suspend fun loadChar(): List<Characters>? {
        Log.d("CountryRepositoryImp", "loadChar: ")
       return firebaseApi.loadChar()
    }

    override suspend fun loadEvents(): List<Events>? {
        val allSnapshot = firebaseApi.loadEvent()
        val allEvents = mutableListOf<Events>()
        for (eventsSnapshot in allSnapshot.children){
            val event=eventsSnapshot.getValue(Events::class.java)
            if (event!=null){
                allEvents.add(event)
            }
        }
        return allEvents
    }

    override suspend fun loadCharacters(): List<Characters>? {
       return firebaseApi.loadCharacters()
    }


}