package com.example.tingoconkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tingoconkotlin.adaptadores.TareasAdapter
import com.example.tingoconkotlin.clases.Tareas
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.arrayListOf as arrayListOf1


class MainActivity : AppCompatActivity(), TareasAdapter.CellClickListener {
    val tareas= arrayListOf1<Tareas>()
    var modo = "usuario"
    var posicion = Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

/*
        vistaCrearTareas.visibility=View.GONE
        vistaEjecucionTareas.visibility=View.GONE
        vistamenubotones.visibility=View.GONE
        recyclerListaTareas.visibility=View.GONE
        recyclerEliminarTareas.visibility=View.GONE
        vistamenu.visibility=View.GONE
        vistaAgregarResponsable.visibility=View.GONE
 */


        //Funcion boton Menu
        menu.setOnClickListener {
        vistaCrearTareas.visibility=View.GONE
        vistaEjecucionTareas.visibility=View.GONE
        vistamenubotones.visibility=View.GONE
        recyclerListaTareas.visibility=View.GONE
        vistamenu.visibility=View.VISIBLE
        vistaAgregarResponsable.visibility=View.GONE
        modo = "administrador"
        }

        //Funcion boton Salir
        salir.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        /*
        * Funciones del Menu Administrador
        *
        * */
        //Boton Volver
        botonVolver.setOnClickListener {
            vistaCrearTareas.visibility=View.GONE
            vistaEjecucionTareas.visibility=View.GONE
            vistamenubotones.visibility=View.VISIBLE
            recyclerListaTareas.visibility=View.VISIBLE
            vistamenu.visibility=View.GONE
            vistaAgregarResponsable.visibility=View.GONE
            modo = "usuario"
        }
        //Boton Agregar Tarea
        botonAgregarTarea.setOnClickListener {
            vistaCrearTareas.visibility=View.VISIBLE
            vistaEjecucionTareas.visibility=View.GONE
            vistamenubotones.visibility=View.GONE
            recyclerListaTareas.visibility=View.GONE
            vistamenu.visibility=View.GONE
            vistaAgregarResponsable.visibility=View.GONE
            modo = "administrador"
        }
        //Boton Eliminar Tarea
        botonEliminarTarea.setOnClickListener {
            vistaCrearTareas.visibility = View.GONE
            vistaEjecucionTareas.visibility = View.GONE
            vistamenubotones.visibility = View.GONE
            recyclerListaTareas.visibility = View.VISIBLE
            vistamenu.visibility = View.GONE
            vistaAgregarResponsable.visibility = View.GONE
            modo = "administrador"
        }
        //Agregar Responsable
        botonAgregarResponsable.setOnClickListener {
            vistaCrearTareas.visibility = View.GONE
            vistaEjecucionTareas.visibility = View.GONE
            vistamenubotones.visibility = View.GONE
            recyclerListaTareas.visibility = View.GONE
            vistamenu.visibility = View.GONE
            vistaAgregarResponsable.visibility = View.VISIBLE
            modo = "administrador"
        }
        /*
        *Ver Tarea
         */
        botonAbandonarTarea.setOnClickListener {
            vistaCrearTareas.visibility = View.GONE
            vistaEjecucionTareas.visibility = View.GONE
            vistamenubotones.visibility = View.VISIBLE
            recyclerListaTareas.visibility = View.VISIBLE
            vistamenu.visibility = View.GONE
            vistaAgregarResponsable.visibility = View.GONE
        }

        /*
        * Crear Tarea
        * */
        //boton cancelar
        botonCancelarCrearTarea.setOnClickListener {
            vistaCrearTareas.visibility=View.GONE
            vistaEjecucionTareas.visibility=View.GONE
            vistamenubotones.visibility=View.VISIBLE
            recyclerListaTareas.visibility=View.VISIBLE
            vistamenu.visibility=View.GONE
            vistaAgregarResponsable.visibility=View.GONE
            modo = "usuario"
        }
        //boton agregar paso
        //boton terminar tarea
        //boton agregar ayuda

        /*
        * vista agregar responsable
        * */
        botonCancelarAgregarResponsable.setOnClickListener {
            vistaCrearTareas.visibility=View.GONE
            vistaEjecucionTareas.visibility=View.GONE
            vistamenubotones.visibility=View.VISIBLE
            recyclerListaTareas.visibility=View.VISIBLE
            vistamenu.visibility=View.GONE
            vistaAgregarResponsable.visibility=View.GONE
            modo = "usuario"
        }
        botonGuardarAgregarResponsable.setOnClickListener {
            vistaCrearTareas.visibility=View.GONE
            vistaEjecucionTareas.visibility=View.GONE
            vistamenubotones.visibility=View.VISIBLE
            recyclerListaTareas.visibility=View.VISIBLE
            vistamenu.visibility=View.GONE
            vistaAgregarResponsable.visibility=View.GONE
            modo = "usuario"
        }
        cargarTareas()
        recyclerListaTareas.adapter = TareasAdapter(tareas, this, modo)
        recyclerListaTareas.layoutManager = LinearLayoutManager(this)

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
    if(modo=="usuario"){
        vistaCrearTareas.visibility = View.GONE
        vistaEjecucionTareas.visibility = View.VISIBLE
        vistamenubotones.visibility = View.GONE
        recyclerListaTareas.visibility = View.GONE
        vistamenu.visibility = View.GONE
        vistaAgregarResponsable.visibility = View.GONE
    }
    if(modo=="administrador"){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Atencion!!")
        builder.setMessage("Esta seguro que quiere eliminar la tarea ")
        builder.setPositiveButton("Si",null)
        builder.setNegativeButton("No",null)
        val dialog: AlertDialog =builder.create();
        dialog.show()
        vistaCrearTareas.visibility = View.GONE
        vistaEjecucionTareas.visibility = View.GONE
        vistamenubotones.visibility = View.VISIBLE
        recyclerListaTareas.visibility = View.VISIBLE
        vistamenu.visibility = View.GONE
        vistaAgregarResponsable.visibility = View.GONE
        modo="usuario"
    }

    }



}
