package com.example.timetableapp.framents.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.timetableapp.model.User
import com.example.timetableapp.viewmodel.UserViewModel
import com.example.timetableapp.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.saveButton.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val day = etDay.text.toString()
        val course = etCourse.text.toString()
        val time = etTime.text.toString()

        if(inputCheck(day, course, time)){
            // Create User Object
            val user = User(0, day, course, time)
            // Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else{
            Toast.makeText(requireContext(), "Please fill out all field", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(day: String, course: String, time: String): Boolean{
        return !(TextUtils.isEmpty(day) && TextUtils.isEmpty(course) && time.isEmpty())
    }

}