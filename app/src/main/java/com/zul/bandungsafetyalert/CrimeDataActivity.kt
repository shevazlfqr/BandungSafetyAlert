package com.zul.bandungsafetyalert

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.zul.bandungsafetyalert.databinding.ActivityCrimeDataBinding

class CrimeDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrimeDataBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var crimeAdapter: CrimeAdapter
    private val crimeList = mutableListOf<Crime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrimeDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()

        binding.rvCrimeData.layoutManager = LinearLayoutManager(this)
        crimeAdapter = CrimeAdapter(crimeList)
        binding.rvCrimeData.adapter = crimeAdapter

        loadCrimeData()
    }

    private fun loadCrimeData() {
        firestore.collection("crimeReports")
            .get()
            .addOnSuccessListener { result ->
                crimeList.clear()
                for (document: QueryDocumentSnapshot in result) {
                    val crime = document.toObject(Crime::class.java)
                    crimeList.add(crime)
                }
                crimeAdapter.notifyDataSetChanged()

                if (crimeList.isEmpty()) {
                    binding.tvNoData.visibility = View.VISIBLE
                    binding.rvCrimeData.visibility = View.GONE
                } else {
                    binding.tvNoData.visibility = View.GONE
                    binding.rvCrimeData.visibility = View.VISIBLE
                }
            }
            .addOnFailureListener {
                binding.tvNoData.text = "Failed to load data."
                binding.tvNoData.visibility = View.VISIBLE
            }
    }
}
