package com.zul.bandungsafetyalert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zul.bandungsafetyalert.databinding.ActivityCrimeTipsBinding

class CrimeTipsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrimeTipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrimeTipsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
