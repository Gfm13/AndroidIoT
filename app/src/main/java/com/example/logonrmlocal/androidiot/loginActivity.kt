package com.example.logonrmlocal.androidiot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.content.Intent
import kotlinx.android.synthetic.main.activity_login.*


class loginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //2 - Inicializacao
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.currentUser != null) {
            vaiParaProximaTela()
        }

        botaoEntrar.setOnClickListener {

        //3 - Utilizacao
        mAuth.signInWithEmailAndPassword(
                inputEmail.text.toString(),
                inputSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //val user = mAuth.getCurrentUser()
                        vaiParaProximaTela()
                    } else {
                        exibeMensagemDeErro()
                    }

                    // ...
                }
         }
    }

    private fun exibeMensagemDeErro() {
        Toast.makeText(this, "Usuário ou senha inválida",
                Toast.LENGTH_LONG).show()
    }

    private fun vaiParaProximaTela() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
     }

}
