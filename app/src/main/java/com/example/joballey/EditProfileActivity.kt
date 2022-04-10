package com.example.joballey

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.joballey.databinding.ActivityEditProfileBinding
import com.example.joballey.model.view.UserData
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference

class EditProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding
    lateinit var databaseReference: DatabaseReference
    lateinit var storageReference: StorageReference
    lateinit var imageUri: Uri

    lateinit var name:TextInputEditText
    lateinit var email:TextInputEditText
    lateinit var telNo:TextInputEditText
    lateinit var des:TextInputEditText

    private lateinit var uName:String
    private lateinit var uMail:String
    private lateinit var uPhone:String
    private lateinit var uDes:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val done: Button = binding.doneProfile
        val cancel:Button = binding.cancelProfile
        val back:ImageButton = binding.back

        val backButton = binding.back
        var auth: FirebaseAuth = FirebaseAuth.getInstance()

            name = binding.inputName
            email = binding.inputMail
            telNo = binding.inputPhone
            des = binding.inputDes

        done.setOnClickListener {
            uName = name.toString()
            uMail = email.toString()
            uPhone = telNo.toString()
            uDes = des.toString()

            validateName()
            validateTel()
            validateEmail()

            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            val user = UserData(uName, uMail, uPhone, uDes)

            databaseReference.child(uName).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Upload Failed", Toast.LENGTH_SHORT).show()
            }
        }
        backButton.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }
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

    //        private fun uploadProfilePic() {
//            val packagename = getActivity()?.getPackageName()
//            imageUri = Uri.parse("android.resource://$packagename/${R.drawable.unknown}")
//            storageReference = FirebaseStorage.getInstance().getReference("Users/"+auth.currentUser?.uid)
//            storageReference.putFile(imageUri).addOnSuccessListener {
//                Toast.makeText(this, "Profile Picture updated successfully", Toast.LENGTH_SHORT).show()
//            }.addOnFailureListener{
//                Toast.makeText(this, "Failed to Update Profile Picture", Toast.LENGTH_SHORT).show()
//            }
//        }
    private fun validateName() {
        val name = binding.inputName.text.toString()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
        }
    }


    private fun validateTel() {
        val telNo = binding.inputPhone.text.toString()
        if (TextUtils.isEmpty(telNo)) {
            Toast.makeText(this, "Enter your telephone number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateEmail() {
        val email = binding.inputMail.text.toString()
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show()
        }
    }

}