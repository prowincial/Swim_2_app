package com.example.programovich

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.programovich.Help.Image
import com.example.programovich.Help.Jsons
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add_image.*
import java.time.LocalDate

class Add_image : AppCompatActivity() {

    val jsons = Jsons()
    var birds = ArrayList<Image>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_image)

        birds = jsons.arrayFromJson(intent.getStringExtra("wBird"))

        button.setOnClickListener{
            var goodInput = true
            if (enterBirdName.text.isEmpty()) {
                enterBirdName.error = getString(R.string.no_empty_input_error)
                goodInput = false
            }
            if (enterBirdImage.text.isEmpty()) {
                enterBirdImage.error = getString(R.string.no_empty_input_error)
                goodInput = false
            }
            if (goodInput)
                this.getTagsFromUrl(enterBirdImage.text.toString())
        }

    }




    fun getTagsFromUrl(url: String) {


        Picasso.get().load(url).into(object : com.squareup.picasso.Target {


            @TargetApi(Build.VERSION_CODES.O)
            @androidx.annotation.RequiresApi(Build.VERSION_CODES.O)
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                //imageView.setImageBitmap(bitmap)
                val vision = FirebaseVisionImage.fromBitmap(bitmap!!)
                val labeler = FirebaseVision.getInstance().getOnDeviceImageLabeler()
                labeler.processImage(vision)

                    .addOnSuccessListener {


                        val intent = Intent(this@Add_image, MainActivity::class.java)




                        birds.add(
                            Image(
                                enterBirdName.text.toString(),
                                enterBirdImage.text.toString(),
                                it.map { it.text }.joinToString(" "),
                                LocalDate.now().toString()
                            )
                        )




                            intent.putExtra("sBird", jsons.arrayToJson(birds))
                            startActivity(intent)
                            setResult(RESULT_OK, intent)
                            finish()



                    }
                    .addOnFailureListener {


                        Log.wtf("LAB", it.message)

                    }

                Log.wtf("Neoch:", "loadOk")
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                Log.wtf("Neoch:", "load")

            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                Log.wtf("Neoch:", "loadBad")
                Toast.makeText(this@Add_image,"Invalid URL",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
