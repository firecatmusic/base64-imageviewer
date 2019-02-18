package local.practice.mvrxpractice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class ResultScanActivity : AppCompatActivity() {

    private lateinit var tvResultScan: TextView
    private lateinit var btnRescan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_scan)
        btnRescan = findViewById(R.id.btnScanAgain)
        tvResultScan = findViewById(R.id.tvResultScan)

        val result = intent?.getStringExtra("result")
        tvResultScan.text = result.toString()
        btnRescan.setOnClickListener {
            startActivity(Intent(this@ResultScanActivity, MainActivity::class.java))
        }

    }
}