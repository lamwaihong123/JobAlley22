package com.example.joballey.model

class UserSkills(val uSkills:String){

    companion object{
        fun createSkillsList (numSkills: Int):ArrayList<UserSkills> {
            val userList = ArrayList<UserSkills>()
            for(i in 1..numSkills){

            }
            return userList
        }
    }
}
