package com.example.kotlinapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu?.findItem(R.id.toolbarSearch)
        val searchView = searchItem?.actionView as SearchView

        searchView.isSubmitButtonEnabled = true

        val recycler : RecyclerView = findViewById(R.id.recyclerview)
        val musicAdapter = recycler.adapter as MusicAdapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                musicAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.toolbarFav -> {
            true
        }
        R.id.toolbarSearch -> {
            false
        }
        else -> super.onOptionsItemSelected(item)
    }




    override fun onResume() {
        super.onResume()

        Player.handleComponents(activity = this, usesCardLayout = true)

        val recycler : RecyclerView = findViewById(R.id.recyclerview)
        val musicAdapter = MusicAdapter(musics)
        recycler.adapter = musicAdapter

        val musicSeparator = DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(recycler.context, R.drawable.divider)
        drawable?.let {
            musicSeparator.setDrawable(it)
            recycler.addItemDecoration(musicSeparator)
        }


        val nav : BottomNavigationView = findViewById(R.id.nav)

        nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.page1 -> true
                R.id.page2 -> {
                    val intent = Intent(this, PlaylistActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.page3 -> true
                else -> true
            }
        }

    }

}