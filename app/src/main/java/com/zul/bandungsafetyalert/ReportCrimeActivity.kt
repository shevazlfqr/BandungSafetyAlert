package com.zul.bandungsafetyalert

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.zul.bandungsafetyalert.databinding.ActivityReportCrimeBinding
import java.util.*

class ReportCrimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportCrimeBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportCrimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()

        binding.WaktuKejahatan.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                binding.WaktuKejahatan.setText(String.format("%02d:%02d", selectedHour, selectedMinute))
            }, hour, minute, true).show()
        }

        binding.TanggalKejahatan.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                binding.TanggalKejahatan.setText(String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear))
            }, year, month, day).show()
        }

        binding.btnSubmitReport.setOnClickListener {
            val time = binding.WaktuKejahatan.text.toString()
            val date = binding.TanggalKejahatan.text.toString()
            val description = binding.Kronologi.text.toString()
            val selectedCrimeTypeId = binding.radioGroupCrimeType.checkedRadioButtonId
            val selectedCrimeType = findViewById<RadioButton>(selectedCrimeTypeId)?.text.toString()

            if (time.isNotEmpty() && date.isNotEmpty() && description.isNotEmpty() && selectedCrimeType.isNotEmpty()) {
                val crimeData = hashMapOf(
                    "time" to time,
                    "date" to date,
                    "description" to description,
                    "crimeType" to selectedCrimeType,
                    "timestamp" to System.currentTimeMillis()
                )

                firestore.collection("crimeReports")
                    .add(crimeData)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Laporan berhasil dikirim", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Gagal mengirim laporan", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
