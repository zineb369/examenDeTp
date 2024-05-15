package com.example.tp02

import com.example.tp02.HomeActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var userEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var inscriptionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        inscriptionButton = findViewById(R.id.loginButton)

        inscriptionButton.setOnClickListener {
            val username = userEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (isValidCredentials(username, password)) {
                // Authentification réussie, ouvrir HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                // Authentification échouée, afficher un message d'erreur
                Toast.makeText(this, "Nom d'utilisateur ou mot de passe incorrect",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun isValidCredentials(username: String, password: String): Boolean {
        // Remplacez cette logique par votre mécanisme d'authentification réelle
        return username == "utilisateur" && password == "motdepasse"
    }

}