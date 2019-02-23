package id.practice.mvrxpractice

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import com.ortiz.touchview.TouchImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class PreviewImage2 : AppCompatActivity() {

    private lateinit var ivPreviewImage2: TouchImageView
    private var image = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previewimage2)
        ivPreviewImage2 = findViewById(R.id.tcv_previewImage2)
        ivPreviewImage2.scaleType = ImageView.ScaleType.FIT_CENTER
        if (ivPreviewImage2.isZoomed) { ivPreviewImage2.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT) }
        if (intent.getStringExtra("photo") != null) {
            image = intent.getStringExtra("photo")

            Picasso.get().load(image).into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    e?.printStackTrace()
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    ivPreviewImage2.setImageBitmap(bitmap)
                }
            })
            Log.e("image", image)
        } else if (intent.getByteArrayExtra("photo") != null) {
            val imageByteArray = intent.getByteArrayExtra("photo")
            Log.e("imageByteArray", imageByteArray.toString())
            val bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray!!.size)
            ivPreviewImage2.setImageBitmap(bmp)
        }
    }
}