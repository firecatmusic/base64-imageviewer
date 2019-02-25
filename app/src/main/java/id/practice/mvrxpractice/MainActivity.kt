package id.practice.mvrxpractice

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.bumptech.glide.Glide
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    private lateinit var codeScannerView: CodeScannerView
    private lateinit var codeScanner: CodeScanner
    private lateinit var ivNewLayout: ImageView
//    private lateinit var ivNewLayout: TouchImageView

    private lateinit var bitmap: Bitmap
    private var httpImgUrl = "https://i.imgur.com/D9K9b5z.jpg"

    private var base64ImgUrl = getString(R.string.base64_image_example)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newlayout)

        ivNewLayout = findViewById(R.id.ivNewLayout)
        val cleanImage = base64ImgUrl.replace("data:image/png;base64,", "").replace("data:image/jpeg;base64,", "")
        val decodedString = Base64.decode(cleanImage, Base64.DEFAULT)
        bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        Glide.with(this@MainActivity).load(bitmap).into(ivNewLayout)

        Log.e("base64Image", decodedString.toString())

        ivNewLayout.setOnClickListener {
            //Convert to byte array
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()

            val in1 = Intent(this@MainActivity, PreviewImage2::class.java)
            in1.putExtra("photo", byteArray)
            startActivity(in1)
        }
    }
}
