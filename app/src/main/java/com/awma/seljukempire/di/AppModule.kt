package com.awma.seljukempire.di

import com.awma.seljukempire.data.api.CountryRepository
import com.awma.seljukempire.data.api.FirebaseApi
import com.awma.seljukempire.data.repository.CountryRepositoryImp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage() = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseApi(): FirebaseApi = FirebaseApi()

    @Provides
    @Singleton
    fun provideRepository(fApi: FirebaseApi): CountryRepository = CountryRepositoryImp(fApi)
}