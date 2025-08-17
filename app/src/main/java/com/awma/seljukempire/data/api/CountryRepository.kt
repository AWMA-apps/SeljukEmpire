package com.awma.seljukempire.data.api

import com.awma.seljukempire.data.model.Characters
import com.awma.seljukempire.data.model.Events

interface CountryRepository {
    suspend fun loadCharacters(): List<Characters>?

    suspend fun loadEvents(): List<Events>?

    suspend fun loadChar(): List<Characters>?

}