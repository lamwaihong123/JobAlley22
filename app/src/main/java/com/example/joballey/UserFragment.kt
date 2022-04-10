package com.example.joballey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.example.joballey.databinding.FragmentSkillsBinding
import com.example.joballey.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        val edit1 = view.findViewById<ImageButton>(R.id.edit_detail)
        val edit2 = view.findViewById<ImageButton>(R.id.edit_skill)
        val edit3 = view.findViewById<ImageButton>(R.id.edit_edu)
        edit1.setOnClickListener{
            findNavController().navigate(R.id.action_userFragment_to_editProfileFragment)
        }
        edit2.setOnClickListener{
            findNavController().navigate(R.id.action_userFragment_to_skillsFragment)
        }
        edit3.setOnClickListener{
            findNavController().navigate(R.id.action_userFragment_to_educationFragment)
        }

        return view
    }


}