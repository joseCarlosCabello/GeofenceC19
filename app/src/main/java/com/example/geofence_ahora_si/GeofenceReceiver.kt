package com.example.geofence_ahora_si

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class GeofenceReceiver: BroadcastReceiver()  {
    lateinit var message: String
    lateinit var key: String

   /* override fun onReceive(context: Context?,intent: Intent?){
        if(context!=null){
            val geofencingEvent= GeofencingEvent.fromIntent(intent)
            val geofencingTransition= geofencingEvent.geofenceTransition

            if(geofencingTransition== Geofence.GEOFENCE_TRANSITION_ENTER||
                geofencingTransition== Geofence.GEOFENCE_TRANSITION_DWELL){
                if(intent!=null){
                    key= intent.getStringExtra("key")!!
                    message= intent.getStringExtra("message")!!
                }


                val firebase = Firebase.database
                val reference = firebase.getReference("reminders")
                val reminderListener = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val reminder = snapshot.getValue<Reminder>()
                        if (reminder != null) {
                            MapsActivity.showNotification(context.applicationContext,
                                   "hola"// "Location\nLat: ${reminder.lat} - Lon: ${reminder.lng}"
                                )
                        }
                    }

                   override fun onCancelled(error: DatabaseError) {
                       // println("reminder:onCancelled: ${error.details}")
                    }

                }
                val child = reference.child(key)
                child.addValueEventListener(reminderListener)

                // remove geofence
                val triggeringGeofences = geofencingEvent.triggeringGeofences

                MapsActivity.removeGeofences(context, triggeringGeofences)
            }
        }
    }*/
   override fun onReceive(context: Context?, intent: Intent?) {
       val event = GeofencingEvent.fromIntent(intent)

       if (event.hasError()) {
           return
       }

       if (event.geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER) {
           val geofence = event.triggeringGeofences[0]
           MapsActivity.showNotification(context!!, geofence.requestId)
       }
   }
}