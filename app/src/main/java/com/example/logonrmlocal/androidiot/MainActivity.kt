package com.example.logonrmlocal.androidiot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_iluminacao.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var tempNoFirebase: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // chama firebase
        inicializarFirebase()

        botaoIluminacao.setOnClickListener{
            startActivity(Intent(this, IluminacaoActivity::class.java))
        }

        botaoLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut();
            startActivity(Intent(this, loginActivity::class.java))
        }

    }

    private fun inicializarFirebase() {
        database = FirebaseDatabase.getInstance()
        tempNoFirebase = database.getReference("temp")
        addListenerTemp()
    }

    private fun addListenerTemp() {
        tempNoFirebase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Double::class.java)

                tempTela.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}
