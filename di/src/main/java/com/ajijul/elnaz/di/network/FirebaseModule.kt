package com.ajijul.elnaz.di.network

import com.ajijul.elnaz.data.network.FirebaseAuthSource
import com.ajijul.elnaz.data.network.FirebaseFirestoreDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestoreDataSource(
        firestore: FirebaseFirestore
    ): FirebaseFirestoreDataSource {
        return FirebaseFirestoreDataSource(firestore)
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestoreDataSource(
        auth: FirebaseAuth
    ): FirebaseAuthSource {
        return FirebaseAuthSource(auth)
    }

}