package id.practice.mvrxpractice

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.view.View
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import com.helloanatoly.animatedimagesviewer.ImageViewer

class PreviewImage : AppCompatActivity() {

    private var imgUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)

        val url = intent.getStringExtra("photo")
        val cleanImage = url.replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,", "")
        val decodedString = Base64.decode(cleanImage, Base64.DEFAULT)
        val strings = arrayOf(url)

        ImageViewer.Builder(this, strings)
            .setStartPosition(0)
            .setBackgroundColorRes(R.color.black_alpha35)
            .hideStatusBar(true)
            .exitAnimation(true)
            .show()
    }

    fun load(url: String, v: View, context: AppCompatActivity) {
        val photo = Intent(context, PreviewImage::class.java)
        if (url.startsWith("data:image/png;base64,")) {
            val cleanImage = url.replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,", "")
            val decodedString = Base64.decode(cleanImage, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

        } else if (url.isEmpty()) {
            Toast.makeText(context, "No image can be displayed !!", Toast.LENGTH_SHORT).show()
            return
        } else {

            photo.putExtra("photo", url)

        }


        val p1 = Pair.create(v, "transition")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, p1)
        context.startActivity(photo, options.toBundle())
    }

}