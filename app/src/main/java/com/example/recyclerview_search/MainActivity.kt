package com.example.recyclerview_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val movies = arrayListOf(
        Movie("Raising Arizona", 1987),
        Movie("Vampire's Kiss", 1988),
        Movie("Con Air", 1997),
        Movie("Gone in 60 Seconds", 1997),
        Movie("National Treasure", 2004),
        Movie("The Wicker Man", 2006),
        Movie("Ghost Rider", 2007),
        Movie("Knowing", 2009)
    )

    private var adapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val rv = findViewById(R.id.list_recycler_view) as RecyclerView

        val layoutManager = LinearLayoutManager(getApplicationContext())
        adapter = RecyclerViewAdapter(movies)

        rv.setAdapter(adapter)
        rv.setLayoutManager(layoutManager)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.appbar, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setQueryHint("Search View Hint")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter?.getFilter()?.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                adapter?.getFilter()?.filter(query)
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }
}
