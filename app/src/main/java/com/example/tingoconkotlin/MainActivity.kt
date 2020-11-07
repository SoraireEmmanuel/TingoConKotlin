package com.example.tingoconkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tingoconkotlin.adaptadores.TareasAdapter
import com.example.tingoconkotlin.clases.Tareas
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.arrayListOf as arrayListOf1


class MainActivity : AppCompatActivity(), TareasAdapter.CellClickListener {
    val tareas= arrayListOf1<Tareas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //Funcion boton Menu
        val menuFragment= MenuFragment()
        menu.setOnClickListener {
            menuFragment.show(supportFragmentManager, "BottomSheetDialog")
        }

        //Funcion boton Salir
        salir.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        cargarTareas()
        recyclerListaTareas.adapter =
            TareasAdapter(tareas, this)
        recyclerListaTareas.layoutManager = LinearLayoutManager(this)

        // recyclerMisTareas.setLayoutManager(LinearLayoutManager(this))   equivalente en java

/*
        val adaptador =
            TareasAdapter(tareas, this)
        recyclerListaTareas.setAdapter(adaptador)
  */
    }

    private fun cargarTareas() {
        tareas.add(Tareas("Hacer Mates",null,null,null))
        tareas.add(Tareas("Calentar Comida",null,null,null))
        tareas.add(Tareas("Ver Television",null,null,null))
        tareas.add(Tareas("Lavar Ropa",null,null,null))
        tareas.add(Tareas("Hacer un Te",null,null,null))
        tareas.add(Tareas("Ir a dormir",null,null,null))
    }

    //Codigo para que sea full screen
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onCellClickListener() {
        val intento = Intent(this, EjecucionTareaActivity::class.java)
        startActivity(intento)
    }

}
