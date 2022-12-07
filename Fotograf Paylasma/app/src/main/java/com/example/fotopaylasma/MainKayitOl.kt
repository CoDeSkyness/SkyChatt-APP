package com.example.fotopaylasma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_kayit_ol.*

class MainKayitOl : AppCompatActivity() {

    private  lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kayit_ol)

        auth = FirebaseAuth.getInstance()
    }

    fun kaydet(view: View){

        val email = kayitKullaniciAdi.text.toString()
        val sifre = kayitParola.text.toString()

        auth.createUserWithEmailAndPassword(email, sifre).addOnCompleteListener { task ->
            // asenkron
            if(task.isSuccessful){
                // Diğer aktiviteye geç
                val intent = Intent(this, MessageArea::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Hesabınız Oluşturuldu!", Toast.LENGTH_LONG).show()
            }

        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }

    fun giriseDon(view: View){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

