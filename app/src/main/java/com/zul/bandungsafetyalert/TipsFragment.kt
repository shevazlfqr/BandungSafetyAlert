package com.zul.bandungsafetyalert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zul.bandungsafetyalert.databinding.ActivityCrimeTipsBinding

class TipsFragment : Fragment() {

    private var _binding: ActivityCrimeTipsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityCrimeTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTipsUmum.text = getString(R.string.TipsUmum)
        binding.tvTipsdiRumah.text = getString(R.string.TipsdiRumah)
        binding.tvTipsBelaDiri.text = getString(R.string.TipsBelaDiri)
        binding.tvTipsKejahatanSiber.text = getString(R.string.TipsKejahatanSiber)
        binding.tvTipsTempatUmum.text = getString(R.string.TipsTempatUmum)
        binding.tvTipsTransportasiUmum.text = getString(R.string.TipsTransportasiUmum)
        binding.tvTipsTambahan.text = getString(R.string.TipsTambahan)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
