package com.example.programovich

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.programovich.Fragments.DetailsFragment
import com.example.programovich.Fragments.FragmentPicture
import com.example.programovich.Fragments.SimilarPictures
import com.example.programovich.Help.Jsons

class SecondActivity : AppCompatActivity() {
    companion object {
        const val ARRAY = "lista"
        const val POSITION = "pos"
    }

    private lateinit var fragmentManager: FragmentManager
    private lateinit var bigPhotoFragment: Fragment
    private lateinit var detailsFragment: Fragment
    private lateinit var similarPictures: Fragment
    private var isSwapped:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        fragmentManager = supportFragmentManager

        // val data = intent.getParcelableArrayListExtra<DataItem>(ARRAY)
        val jsons= Jsons()
        val bImage =jsons.itemFromJson(intent.getStringExtra("birdD"))
       // val urls = jsons.arrayFromJson<ArrayList<String>>(intent.getStringExtra("urls"))
        // val position = intent.getIntExtra(POSITION, 0)

        bigPhotoFragment = FragmentPicture.newInstance(bImage.pic)
        detailsFragment = DetailsFragment.newInstance(bImage)
        similarPictures = SimilarPictures.newInstance(bImage.pic)

        fragmentManager.beginTransaction().add(R.id.activity_description, bigPhotoFragment, "photo").commit()
        fragmentManager.beginTransaction().add(R.id.activity_description, detailsFragment, "details").commit()
        fragmentManager.beginTransaction().add(R.id.activity_description, similarPictures, "similars").commit()
        fragmentManager.beginTransaction().hide(detailsFragment).commit()
        fragmentManager.beginTransaction().hide(similarPictures).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_change, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change -> {
                swapFragments()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun swapFragments() {
        if (isSwapped){
            fragmentManager.beginTransaction().hide(detailsFragment).commit()
            fragmentManager.beginTransaction().hide(similarPictures).commit()
            fragmentManager.beginTransaction().show(bigPhotoFragment).commit()
            isSwapped = !isSwapped

        } else{
            fragmentManager.beginTransaction().hide(bigPhotoFragment).commit()
            fragmentManager.beginTransaction().show(detailsFragment).commit()
            fragmentManager.beginTransaction().show(similarPictures).commit()
            isSwapped = !isSwapped
        }

    }
}
