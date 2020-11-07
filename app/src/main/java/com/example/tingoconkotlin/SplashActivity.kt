package com.example.tingoconkotlin

import android.R.attr.logo
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    private var animatorAlpha: ObjectAnimator? = null
    private val ANIMATION_DURATION: Long = 5000
    private val animatorSet: AnimatorSet? = null
    private val DURACION_SPLASH = 6000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val splashanimation = AnimationUtils.loadAnimation(this,R.anim.splasanimation);
        val logo = findViewById<ImageView>(R.id.logo)

        logo.startAnimation(splashanimation)


        Handler().postDelayed(
            Runnable {
                when (getFirstTimeRun(this@SplashActivity)) {
                    0,1 -> {
                        val prefs=getSharedPreferences(getString(R.string.usuario), Context.MODE_PRIVATE)
                        val email = prefs.getString("email", null)
                        if(email!=null) {
                            val intent = Intent(this@SplashActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            val intento = Intent(this@SplashActivity, LoguinActivity::class.java)
                            startActivity(intento)
                        }
                    }
                    2 -> {
                    }
                }
            },
            6000
        )
    }

    fun getFirstTimeRun(contexto: Context): Int {
        val sp: SharedPreferences = contexto.getSharedPreferences("MYAPP", 0)
        val result: Int
        val currentVersionCode = BuildConfig.VERSION_CODE
        val lastVersionCode = sp.getInt("FIRSTTIMERUN", -1)
        result =
            if (lastVersionCode == -1) 0 else if (lastVersionCode == currentVersionCode) 1 else 2
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply()
        return result
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
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }





}
