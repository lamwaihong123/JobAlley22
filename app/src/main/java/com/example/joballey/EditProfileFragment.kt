package com.example.joballey

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.joballey.databinding.FragmentEditProfileBinding
import com.example.joballey.model.view.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class EditProfileFragment : Fragment() {
    private lateinit var binding:FragmentEditProfileBinding

    private lateinit var auth:FirebaseAuth
    private lateinit var databaseReference:DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri : Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(layoutInflater, container, false)
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.commit()
        val backButton = binding.back
//        auth = FirebaseAuth.getInstance()
//        val uid = auth.currentUser?.uid
//        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//        binding.doneProfile.setOnClickListener{
//
//            val name = binding.inputName.text.toString()
//            val email = binding.inputMail.text.toString()
//            val phone = binding.inputPhone.text.toString()
//            val des = binding.inputDes.text.toString()
//
//            val user = UserData(name,email,phone,des)
//            if(uid!=null){
//                databaseReference.child(uid).setValue(user).addOnCompleteListener{
//                    if (it.isSuccessful){
//                        uploadProfilePic()
//                    }
//                }
//            }
//        }
        auth = FirebaseAuth.getInstance()
        binding.doneProfile.setOnClickListener{

            val name = binding.inputName.text.toString()
            val email = binding.inputMail.text.toString()
            val telNo = binding.inputPhone.text.toString()
            val des = binding.inputDes.text.toString()

            validateName()
            validateTel()
            validateEmail()

            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            val user = UserData(name,email,telNo,des)

            databaseReference.child(name).setValue(user).addOnSuccessListener {
                Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Upload Failed", Toast.LENGTH_SHORT).show()
            }
        }
        backButton.setOnClickListener{
            findNavController().navigate(R.id.action_editProfileFragment_to_userFragment)
        }
        return binding.root
    }
//    private fun uploadDatabase(){
//        auth = FirebaseAuth.getInstance()
//        binding.doneProfile.setOnClickListener{
//
//            val name = binding.inputName.text.toString()
//            val email = binding.inputMail.text.toString()
//            val telNo = binding.inputPhone.text.toString()
//            val des = binding.inputDes.text.toString()
//
//            validateName()
//            validateTel()
//            validateEmail()
//
//            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
//            val user = UserData(name,email,telNo,des)
//
//                databaseReference.child(name).setValue(user).addOnSuccessListener {
//                    Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_SHORT).show()
//                }.addOnFailureListener {
//                    Toast.makeText(requireContext(), "Upload Failed", Toast.LENGTH_SHORT).show()
//                }
//        }
//    }

    private fun uploadProfilePic() {
        val packagename = getActivity()?.getPackageName()
        imageUri = Uri.parse("android.resource://$packagename/${R.drawable.unknown}")
        storageReference = FirebaseStorage.getInstance().getReference("Users/"+auth.currentUser?.uid)
        storageReference.putFile(imageUri).addOnSuccessListener {
            Toast.makeText(requireContext(), "Profile Picture updated successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(requireContext(), "Failed to Update Profile Picture", Toast.LENGTH_SHORT).show()
        }
    }
    private fun validateName() {
        val name = binding.inputName.text.toString()
        if(TextUtils.isEmpty(name)) {
            Toast.makeText(requireContext(), "Enter your name", Toast.LENGTH_SHORT).show()
        }
    }


    private fun validateTel() {
        val telNo = binding.inputPhone.text.toString()
        if(TextUtils.isEmpty(telNo)){
            Toast.makeText(requireContext(), "Enter your telephone number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateEmail() {
        val email = binding.inputMail.text.toString()
        if(TextUtils.isEmpty(email)) {
            Toast.makeText(requireContext(), "Enter your email", Toast.LENGTH_SHORT).show()
        }
    }

}