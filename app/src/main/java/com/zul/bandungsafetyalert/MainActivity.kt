package com.zul.bandungsafetyalert

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.zul.bandungsafetyalert.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up button click listeners
        binding.btnReportCrime.setOnClickListener {
            startActivity(Intent(this, ReportCrimeActivity::class.java))
        }

        binding.btnCrimeData.setOnClickListener {
            startActivity(Intent(this, CrimeDataActivity::class.java))
        }

        binding.btnCrimeTips.setOnClickListener {
            startActivity(Intent(this, CrimeTipsActivity::class.java))
        }

        // Fetch the FCM token
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        private const val TAG = "FCM_TOKEN"
    }
}
