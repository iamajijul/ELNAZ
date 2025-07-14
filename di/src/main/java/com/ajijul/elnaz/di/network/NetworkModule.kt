package com.ajijul.elnaz.di.network

import com.ajijul.elnaz.data.network.FirebaseDataSource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDataSource(
        firestore: FirebaseFirestore
    ): FirebaseDataSource {
        return FirebaseDataSource(firestore)
    }

}