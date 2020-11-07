package com.example.tingoconkotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_loguin.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoguinActivity : AppCompatActivity() {

    private val GOOGLE_SING_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_loguin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        session()
    }

    private fun session() {
        //esto es para el splash
        val prefs=getSharedPreferences(getString(R.string.usuario), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        //val provider = prefs.getString("provider", null)

        googleboton.setOnClickListener{
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient= GoogleSignIn.getClient(this,googleConf)
            startActivityForResult(googleClient.signInIntent, GOOGLE_SING_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==GOOGLE_SING_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account:GoogleSignInAccount? = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credencial:AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credencial)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                guardarLogin(account.email ?:"")
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                        }
                } else {

                }
            }
            catch (e:ApiException){
                showAlert()
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No se pudo autenticar")
        builder.setPositiveButton("Aceptar",null)
        val dialog:AlertDialog=builder.create();
        dialog.show()
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

    private  fun guardarLogin(email:String){
        val prefs=getSharedPreferences(getString(R.string.usuario), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.apply()  //Esta linea guarda lo realizaado en el sharedpreferences
        /*para borrar las prefeerencias se hace
                val prefs=getSharedPreferences(getString(R.string.usuario), Context.MODE_PRIVATE).edit()
                prefs.clear()
                prefs.apply()
         */
    }

}
