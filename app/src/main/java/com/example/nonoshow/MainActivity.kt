package com.example.nonoshow

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import android.widget.ImageButton
import kotlinx.android.synthetic.main.fragment_slideshow.*
import android.R.attr.*
import android.content.Context
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
Log.i("set","created")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        MyApplication.isLogined = false
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_search_by_phoneNum, R.id.nav_signIn, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        contextForList = this
    }
    @SuppressLint("WrongViewCast")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        nickname = findViewById(R.id.menuNickNameText)
        signOutText = findViewById(R.id.signOutText)
        signOutText!!.visibility = View.INVISIBLE
        signOutText!!.setOnClickListener{
            changeState("logout",LOGOUT)
        }
        return true
    }

    override fun onResume(){
        super.onResume()
Log.i("set","resume~")
    }

    override fun onRestart(){
        super.onRestart()
Log.i("set","restart!")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        nickname!!.setOnClickListener{
            if(!MyApplication.isLogined)
                navController.navigate(R.id.nav_signIn)
        }
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    companion object {
        private const val LOGIN: Int = 0
        const val LOGOUT: Int = 1
        var nickname: TextView? = null
        @SuppressLint("StaticFieldLeak")
        var signOutText: TextView? = null
        var contextForList: Context? = null

        fun changeState(data: String, index: Int) {
            when (index) {
                LOGIN -> {
                    var id = data
                    nickname!!.text = id
                    signOutText!!.visibility = View.VISIBLE
                }
                LOGOUT -> {
                    MyApplication.logout()
                    nickname!!.text = "로그인 해 주세요"
                    signOutText!!.visibility = View.INVISIBLE
                }
            }
        }
    }

}
