package com.ajijul.elnaz.data.network

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseFirestoreDataSource(val firestore: FirebaseFirestore) {

    suspend inline fun <reified T : Any> getFirestoreDocument(
        collection: String,
        documentId: String
    ): T? {
        val document = firestore.collection(collection).document(documentId).get().await()
        return if (document.exists()) document.toObject(T::class.java) else null
    }

    suspend fun <T : Any> setFirestoreDocument(
        collection: String,
        documentId: String,
        data: T
    ) {
        firestore.collection(collection).document(documentId).set(data).await()
    }

    suspend inline fun <reified T : Any> getFirestoreCollection(
        collection: String
    ): List<T> {
        val snapshot = firestore.collection(collection).get().await()
        return snapshot.documents.mapNotNull { it.toObject(T::class.java) }
    }

}