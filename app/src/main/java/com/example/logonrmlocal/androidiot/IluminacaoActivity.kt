package com.example.logonrmlocal.androidiot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_iluminacao.*

class IluminacaoActivity : AppCompatActivity() {
    lateinit var database: FirebaseDatabase
    lateinit var ledNoFirebase:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iluminacao)
        inicializarFirebase()
    }

    private fun inicializarFirebase() {
        database = FirebaseDatabase.getInstance()
        ledNoFirebase = database.getReference("led")
        addListenerLed()
    }

    private fun addListenerLed() {
        ledNoFirebase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Int::class.java)
                when (value) {
                    0 -> {
                        if (switcher.displayedChild != 0) {
                            switcher.showPrevious()
                        }

                    }
                    1 -> if (switcher.displayedChild != 1) {
                        switcher.showNext()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun alterarStatus(v: View) {
        when(switcher.displayedChild) {
            0-> {ligar()}
            1-> {desligar()}
        }
    }

    fun ligar() {
        ledNoFirebase.setValue(1)
        switcher.showNext()
    }

    fun desligar () {
        ledNoFirebase.setValue(0)
        switcher.showPrevious()
    }

}
