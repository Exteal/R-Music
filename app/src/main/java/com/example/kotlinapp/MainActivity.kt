package com.example.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(R.id.playerFragment, PlayerMP3Fragment())
            .add(R.id.recyclerFragment, MusicFragment())
            .commit()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu?.findItem(R.id.toolbarSearch)
        val searchView = searchItem?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "Search here"

        val recycler : RecyclerView = findViewById(R.id.recyclerview)

        val musicAdapter = recycler.adapter as? MusicAdapter
        val playListAdapter = recycler.adapter as? PlayListAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                musicAdapter?.filter?.filter(query)
                playListAdapter?.filter?.filter(query)
                return false
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

        Player.handleComponents(activity = this, layoutType = PlayerMp3Layout::class.java)

        val nav : BottomNavigationView = findViewById(R.id.nav)

        nav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.page1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.recyclerFragment, MusicFragment())
                        .commit()
                    true
                }
                R.id.page2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.recyclerFragment, PlayListFragment())
                        .commit()
                    true
                }

                R.id.page3 -> true
                else -> true
            }
        }

    }

}