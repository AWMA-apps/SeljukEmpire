package com.awma.seljukempire.data.api

import android.util.Log
import com.awma.seljukempire.constants.Characters_FD
import com.awma.seljukempire.constants.Events_FD
import com.awma.seljukempire.constants.State
import com.awma.seljukempire.data.model.Characters
import com.awma.seljukempire.data.model.Events
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshots
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseApi {
    private val database = FirebaseDatabase.getInstance()
    private val dref = database.getReference()

    suspend fun loadCharacters(): List<Characters>? = suspendCoroutine { corto ->
        dref.child(Characters_FD).child(State)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val charList = snapshot.children.mapNotNull {
                        it.getValue(Characters::class.java)
                    }
                    corto.resume(charList)
                }

                override fun onCancelled(error: DatabaseError) {
                    corto.resume(null)
                }
            })
    }

    suspend fun loadChar(): List<Characters>? {
        val query: Query = dref.child(Characters_FD).child(State)
        val sanpshot = query.awaitSingle()
        val charList = sanpshot.children.mapNotNull {
            it.getValue(Characters::class.java)
        }
        Log.d("FirebaseApi", "loadChar: $charList")
        return charList
    }

    suspend fun loadEvent(): DataSnapshot {
        val query = dref.child(Events_FD).child(State)
        val snapshot = query.get().await()
        return snapshot
    }

    private suspend fun Query.awaitSingle(): DataSnapshot = suspendCancellableCoroutine { corot ->
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                corot.resume(snapshot)
                Log.d("FirebaseApi", "onDataChange: $snapshot")
            }

            override fun onCancelled(error: DatabaseError) {
                corot.resumeWithException(error.toException())
            }
        }
        addListenerForSingleValueEvent(listener)
        corot.invokeOnCancellation {
            removeEventListener(listener)
            Log.d("FirebaseApi", "invokeOnCancellation")
        }
    }
}