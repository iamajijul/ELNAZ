package com.ajijul.elnaz.data.repository.auth

import com.ajijul.elnaz.data.network.FirebaseAuthSource
import com.ajijul.elnaz.data.network.FirebaseFirestoreDataSource
import com.ajijul.elnaz.data.network.firebase.FirestoreCollections
import com.ajijul.elnaz.domain.auth.AuthRepository
import com.ajijul.elnaz.domain.auth.UserModel
import com.ajijul.elnaz.domain.auth.UserRole
import javax.inject.Inject

class AuthRepositoryImplementation @Inject constructor(
    private val authDataSource: FirebaseAuthSource,
    private val firestoreDataSource: FirebaseFirestoreDataSource
) : AuthRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Result<UserModel?> {
        return try {
            val user = authDataSource.login(email, password)
            if (user != null) {
                val profile = firestoreDataSource.getFirestoreDocument<UserModel>(
                    FirestoreCollections.USERS,
                    user.uid
                )
                Result.success(profile)
            } else {
                Result.failure(Exception(""))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        role: UserRole
    ): Result<UserModel?> {
        return try {
            val user = authDataSource.signup(email, password)
            if (user != null) {
                val userModel = UserModel(
                    uid = user.uid,
                    email = email,
                    name = name,
                    role = role,
                    createdAt = System.currentTimeMillis()
                )

                firestoreDataSource.setFirestoreDocument(
                    FirestoreCollections.USERS,
                    user.uid,
                    userModel
                )
                Result.success(userModel)
            } else {
                Result.failure(Exception(""))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun currentUser(): Result<UserModel?> {
        TODO("Not yet implemented")
    }

    /* private fun users()=db.collection("users")
     override suspend fun login(email:String,password:String):User{ val res=auth.signInWithEmailAndPassword(email,password).await(); val uid=res.user?.uid?:error("no uid"); val snap=users().document(uid).get().await(); val user= if(snap.exists()) snap.toUser(uid) else ensure(uid,email); store.save(user); return user }
     override suspend fun register(name:String,email:String,password:String,role:UserRole):User{ val res=auth.createUserWithEmailAndPassword(email,password).await(); val uid=res.user?.uid?:error("no uid"); val now=System.currentTimeMillis(); val payload= mapOf("email" to email,"name" to name,"role" to role.name,"createdAt" to now); users().document(uid).set(payload, SetOptions.merge()).await(); val user=User(uid,email,name,role,now); store.save(user); return user }
     override suspend fun logout(){ auth.signOut(); store.clear() }
     override suspend fun currentUser():User?{ val cached=store.get(); if(cached!=null) return cached; val fb=auth.currentUser?:return null; val snap=users().document(fb.uid).get().await(); val user= if(snap.exists()) snap.toUser(fb.uid) else ensure(fb.uid, fb.email?:""); store.save(user); return user }
     private suspend fun ensure(uid:String,email:String):User{ val now=System.currentTimeMillis(); val def= mapOf("email" to email,"name" to email.substringBefore('@'),"role" to UserRole.STAFF.name,"createdAt" to now); users().document(uid).set(def, SetOptions.merge()).await(); return User(uid,email, def["name"] as String, UserRole.STAFF, now)}*/
}