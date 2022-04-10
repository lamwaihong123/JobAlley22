package com.example.joballey

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.joballey.databinding.AddSkillBinding
import com.example.joballey.model.UserSkills
import com.example.joballey.model.view.UserAdapter
import java.util.*

class SkillsDialogFragment : DialogFragment() {
    private lateinit var binding: AddSkillBinding
    private lateinit var userList: ArrayList<UserSkills>
    private lateinit var userAdapter: UserAdapter
    private lateinit var recv: RecyclerView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AddSkillBinding.inflate(LayoutInflater.from(context))

        val addSkill = binding.userSkill
        val addDialog = AlertDialog.Builder(requireContext())

        recv = binding.root.findViewById(R.id.skillsList)
        userList = ArrayList<UserSkills>()
        userAdapter = UserAdapter(userList)
        recv.adapter = userAdapter
        recv.layoutManager = LinearLayoutManager(requireContext())

        addDialog.setView(binding.root)
        addDialog.setPositiveButton("OK") { dialog, _ ->
            val skill = addSkill.text.toString()
            userList.add(UserSkills("Skills: $skill"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(requireContext(), "Skill Added Successfully", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
        }

        addDialog.setNegativeButton("Cancel") { dialog, _ ->

            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }


        return addDialog.create()
    }
}