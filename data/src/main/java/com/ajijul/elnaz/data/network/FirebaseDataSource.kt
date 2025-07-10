package com.ajijul.elnaz.data.network

import com.ajijul.elnaz.data.local.entity.ItemEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseDataSource(private val firestore: FirebaseFirestore) {
    suspend fun syncItems(items: List<ItemEntity>) {
        items.forEach { item ->
            firestore.collection("items").document(item.barcode).set(item).await()
        }
    }

    suspend fun getItems(): List<ItemEntity> {
        return firestore.collection("items").get().await().toObjects(ItemEntity::class.java)
    }
}