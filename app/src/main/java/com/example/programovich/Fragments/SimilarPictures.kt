package com.example.programovich.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.programovich.R
import com.squareup.picasso.Picasso

class SimilarPictures : Fragment() {

    companion object {

        private const val KEY = "data"

        @JvmStatic
        fun newInstance(url: String) =
            SimilarPictures().apply {
                arguments = Bundle().apply {
                    putString(KEY, url)
                }
            }
    }



   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                             savedInstanceState: Bundle?
   ): View? {

       val view = inflater.inflate(R.layout.similar_fragment, container, false)

       val img1 = view.findViewById<ImageView>(R.id.img1)
       val img2 = view.findViewById<ImageView>(R.id.img2)
       val img3 = view.findViewById<ImageView>(R.id.img3)
       val img4 = view.findViewById<ImageView>(R.id.img4)
       val img5 = view.findViewById<ImageView>(R.id.img5)
       val img6 = view.findViewById<ImageView>(R.id.img6)
       val imgList: ArrayList<ImageView> = arrayListOf(img1, img2, img3, img4, img5, img6)

       var urls: ArrayList<String> = ArrayList()
       urls.add("https://ochepyatki.ru/upfiles/albums/4211/f_111866.jpg")
       urls.add("https://i.ytimg.com/vi/SFyjRx2Zbfc/maxresdefault.jpg")
       urls.add("http://goodimg.ru/img/kartinki-zverey5.jpg")
       urls.add("https://files.adme.ru/files/news/part_194/1946865/28481515-image-crop-640x596-1543311595-728-c79ada7a44-1543390977.jpg")
       urls.add("https://i.ytimg.com/vi/M64fYJgtl-Q/maxresdefault.jpg")
       urls.add("https://cdn.fishki.net/upload/post/2016/09/15/2075075/tn/22dbac84824a7f308b337dbd20b7eb81.png")

       for (i in 0..5) {
           imgList[i].visibility = View.VISIBLE
           Picasso.get().load(urls[i]).into(imgList[i])
       }

       return view
   }

}