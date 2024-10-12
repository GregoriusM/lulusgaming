package com.ubaya.lulusgaming.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentWhoBinding
import com.ubaya.lulusgaming.viewmodel.WhoViewModel

class WhoFragment : Fragment() {
    private lateinit var binding: FragmentWhoBinding
    private val whoViewModel : WhoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhoBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_who, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtDeskripsi.text = "Lulus Gaming E-Sport merupakan suatu tim yang terdiri dari 2 orang mahasiswa bernama Gregorius Mario dan Brian Owen. " +
                "Mereka berdua ialah Mahasiswa Teknik Informatika Universitas Surabaya (UBAYA)."

        Picasso.get()
            .load(R.drawable.duo)
            .into(binding.imageViewTeams)

        binding.btnLike.text = "${whoViewModel.like}"

        binding.btnLike.setOnClickListener{
            whoViewModel.like++
            binding.btnLike.text = "${whoViewModel.like}"
        }
    }

}