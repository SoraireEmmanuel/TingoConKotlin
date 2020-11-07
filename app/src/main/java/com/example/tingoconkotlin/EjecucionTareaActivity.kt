package com.example.tingoconkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_agregar_responsable.*
import kotlinx.android.synthetic.main.activity_ejecucion_tarea.*
import kotlinx.android.synthetic.main.activity_ejecucion_tarea.dummy_button
import kotlinx.android.synthetic.main.activity_ejecucion_tarea.fullscreen_content
import kotlinx.android.synthetic.main.activity_ejecucion_tarea.fullscreen_content_controls


class EjecucionTareaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ejecucion_tarea)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

}
