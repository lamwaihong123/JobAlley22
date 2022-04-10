package com.example.joballey

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.joballey.databinding.FragmentSkillsBinding
import com.example.joballey.model.UserSkills
import com.example.joballey.model.view.UserAdapter
import java.util.*

class SkillsFragment : Fragment() {
    private lateinit var binding:FragmentSkillsBinding
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserSkills>
    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSkillsBinding.inflate(layoutInflater, container, false)

        val okButton = binding.addSkill
        val skillDia = SkillsDialogFragment()
        val addsBtn = binding.newSkills

        userList = ArrayList<UserSkills>()
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.commit()

        recv = binding.skillsList
        userList = UserSkills.createSkillsList(20)

        userAdapter = UserAdapter(userList)
        recv.adapter = userAdapter
        recv.layoutManager = LinearLayoutManager(requireContext())

        addsBtn.setOnClickListener {
            skillDia.show(fragmentManager,null) }

        okButton.setOnClickListener {
            findNavController().navigate(R.id.action_skillsFragment_to_userFragment)
        }

            return binding.root

    }





}