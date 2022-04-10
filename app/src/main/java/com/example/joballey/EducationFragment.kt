package com.example.joballey

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.joballey.databinding.FragmentEducationBinding

class EducationFragment : Fragment() {
    private lateinit var binding: FragmentEducationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_education, container, false)

        val okButton = view.findViewById<ImageButton>(R.id.add_edu)
        okButton.setOnClickListener{
            okButton.setOnClickListener {
                findNavController().navigate(R.id.action_educationFragment_to_userFragment)

            }
           // findNavController().navigate(R.id.action_educationFragment_to_userFragment)
        }

        return view
    }


}