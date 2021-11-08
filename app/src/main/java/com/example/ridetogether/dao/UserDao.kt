package com.example.ridetogether.dao

import com.example.ridetogether.modals.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class UserDao {

    private val db = FirebaseFirestore.getInstance()
    private val userCollection = db.collection("users")
    private val auth = FirebaseAuth.getInstance()

    fun addUser(user : User?){
        user?.let {
            userCollection.document(auth.currentUser!!.uid).set(user)
        }
    }

    fun getUser(uid: String) : Task<DocumentSnapshot> {
        return userCollection.document(uid).get()
    }

}