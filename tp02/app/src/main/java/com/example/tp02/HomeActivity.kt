package com.example.tp02

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnAddProject = findViewById<Button>(R.id.btnAddProjects)
        val btnVoirProjets = findViewById<Button>(R.id.btnVoirProjects)

        // Gestion du bouton "Ajouter un projet"
        btnAddProject.setOnClickListener { view: View ->
            startActivity(Intent(this@HomeActivity, DetailsProjectActivity::class.java))
        }

        // Gestion du bouton "Voir mes projets"
        btnVoirProjets.setOnClickListener {view: View ->
            startActivity(Intent(this@HomeActivity, ListProjectsActivity::class.java))
        }
    }
}