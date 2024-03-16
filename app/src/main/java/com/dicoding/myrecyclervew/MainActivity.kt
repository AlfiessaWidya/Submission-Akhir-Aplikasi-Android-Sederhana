package com.dicoding.myrecyclervew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFilms: RecyclerView
    private val list = ArrayList<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionbar = supportActionBar
        actionbar!!.title = "Rekomendasi Film"

        rvFilms = findViewById(R.id.rv_heroes)
        rvFilms.setHasFixedSize(true)

        list.addAll(getListHeroes())

        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvFilms.layoutManager = LinearLayoutManager(this)
            }

            R.id.action_grid -> {
                rvFilms.layoutManager = GridLayoutManager(this, 2)
            }

            R.id.action_about -> {
                val intentAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentAbout)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListHeroes(): ArrayList<Film> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataReleased = resources.getStringArray(R.array.data_released)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataActor = resources.getStringArray(R.array.data_actor)
        val listFilms = ArrayList<Film>()
        for (i in dataName.indices) {
            val film = Film(dataName[i], dataDescription[i], dataPhoto[i], dataReleased[i], dataRating[i], dataActor[i])
            listFilms.add(film)
        }
        return listFilms
    }

    private fun showRecyclerList() {
        rvFilms.layoutManager = LinearLayoutManager(this)
        val listFilmAdapter = ListFilmAdapter(list)
        rvFilms.adapter = listFilmAdapter

        listFilmAdapter.setOnItemClickCallback(object: ListFilmAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Film) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(film: Film) {
        Toast.makeText(this, "Kamu Memilih " + film.name, Toast.LENGTH_SHORT).show()
    }
}