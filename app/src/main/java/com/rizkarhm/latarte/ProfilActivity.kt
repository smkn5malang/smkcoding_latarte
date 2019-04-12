package com.rizkarhm.latarte

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profil.*
import kotlinx.android.synthetic.main.app_bar_profil.*
import kotlinx.android.synthetic.main.content_profil.*
import kotlinx.android.synthetic.main.nav_header_profil.*

class ProfilActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecipesAdapter.ViewHolder>? = null

    var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        setSupportActionBar(toolbar)
//
//        layoutManager = LinearLayoutManager(this)
//        mRecyclerView.layoutManager = layoutManager
//
//        adapter = RecipesAdapter()
//        mRecyclerView.adapter = adapter


        fab.setOnClickListener {
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //WebView
        mWebView = findViewById(R.id.myWebView)

        mWebView!!.webViewClient = MyWebViewClient()
        mWebView!!.webChromeClient = WebChromeClient()
        mWebView!!.loadUrl("https://www.fimela.com/tag/resep-kue")

        mWebView!!.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.INVISIBLE
                super.onPageFinished(view, url)
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                progressBar.visibility = View.INVISIBLE
                super.onPageCommitVisible(view, url)
            }

        }


    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (Uri.parse(url).host == "https://www.fimela.com/tag/resep-kue") {
                return false
            } else {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                return true
            }
        }

    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.profil, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                R.id.action_bar_container
            }
            R.id.nav_favorite -> {

            }
            R.id.nav_history -> {

            }
            R.id.nav_setting-> {

            }
            R.id.nav_login -> {
                val intent = Intent(this, LoginActivity::class.java )
                startActivity(intent)
            }
            R.id.nav_about -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
