package com.example.programovich
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem
import com.example.programovich.Help.Image
import com.example.programovich.Help.Jsons
import com.example.programovich.Help.SwipeToDeleteCallback
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate


class MainActivity : AppCompatActivity() {

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    var images: ArrayList<Image> = ArrayList()
    val jsons = Jsons()
    val gson=Gson()
   var urls: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDataFromJson("sBird")

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerView.adapter = RecyclerAdapter(images)
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                val adapter = recyclerView.adapter as RecyclerAdapter
                adapter.removeAt(p0.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.manu, menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("isBirds", jsons.arrayToJson(images))

    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add -> {
            val intentAbout = Intent(this, Add_image::class.java)
            val dab = jsons.arrayToJson(images)
            intentAbout.putExtra("wBird", dab)

            startActivityForResult(intentAbout, 1)
            true

        }

        else -> {

            super.onOptionsItemSelected(item)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val sImage = savedInstanceState?.getString("isBirds")

        if (sImage != null)
            images = jsons.arrayFromJson(sImage)

    }
    fun loadDataFromJson(name: String) {
       loadThreeItems()
       loadurls()

        val savedString = intent.getStringExtra(name)

        val type = object : TypeToken<ArrayList<Image>>() {}.type

        if (savedString != null)


            images = gson.fromJson(savedString, type)

    }

   fun loadThreeItems(){
       images.add(
           Image(
               "cat",
               "https://media.alienwarearena.com/media/1327-a.jpg",
               "Cat Pet Monochrome Textile Fur Ragdoll",
               LocalDate.now().toString()
           )
       )
       images.add(
           Image(
               "gadkiy ",
               "http://personal.psu.edu/xqz5228/jpg.jpg",
               "Fun Toy Goggles Space Image Leisure",
               LocalDate.now().toString()
           )
       )
       images.add(
           Image(
               "zhaba",
               "http://takala.fr/wp-content/uploads/2019/01/27964054663_38a074335e_b.jpg",
               "Insect Vegetable Food",
               LocalDate.now().toString()
           )
       )
       jsons.arrayToJson(images)
   }
    fun loadurls(){

        urls.add("https://ochepyatki.ru/upfiles/albums/4211/f_111866.jpg")
        urls.add("https://i.ytimg.com/vi/SFyjRx2Zbfc/maxresdefault.jpg")
        urls.add("http://goodimg.ru/img/kartinki-zverey5.jpg")
        urls.add("https://ochepyatki.ru/upfiles/albums/4211/f_111866.jpg")
        urls.add("https://i.ytimg.com/vi/SFyjRx2Zbfc/maxresdefault.jpg")
        urls.add("http://goodimg.ru/img/kartinki-zverey5.jpg")
        jsons.arrayToJson(urls)

    }

}


