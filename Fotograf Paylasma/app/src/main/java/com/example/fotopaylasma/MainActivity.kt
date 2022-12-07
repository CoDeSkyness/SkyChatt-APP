package com.example.fotopaylasma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private  lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser
        if(guncelKullanici != null){
          val intent = Intent(this, MessageArea::class.java)
          startActivity(intent)
          finish()
        }
    }

    fun girisYap(view: View){

        auth.signInWithEmailAndPassword(
            girisKullaniciAdi.text.toString(),
            girisParola.text.toString()).addOnCompleteListener { task ->

            if(task.isSuccessful){

                val guncelKullanici = auth.currentUser?.email.toString()
                Toast.makeText(this, "Hoşgeldin ${guncelKullanici}", Toast.LENGTH_LONG).show()

                val intent = Intent(this, MessageArea::class.java)
                startActivity(intent)
                finish()


            }
          }.addOnFailureListener { exceprion ->
            Toast.makeText(this, exceprion.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    fun kayitOl(view: View){
        val intent = Intent(applicationContext, MainKayitOl()::class.java)
        startActivity(intent)
        finish()
    }

}