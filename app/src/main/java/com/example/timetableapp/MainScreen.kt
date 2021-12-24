package com.example.timetableapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.timetableapp.databinding.ActivityMainScreenBinding
import com.example.timetableapp.viewmodel.SetupUserViewModel
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import androidx.core.view.drawToBitmap as drawToBitmap1

class MainScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding

    private lateinit var setupUserViewModel: SetupUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setupActionBarWithNavController(findNavController(R.id.fragment))

        setupUserViewModel = ViewModelProvider(this).get(SetupUserViewModel::class.java)

        setupUserViewModel.readAllData.observe(this, Observer {
            val data = it

            binding.tvInstitution.text = data.institution
            binding.tvName.text = data.name
            binding.tvDepartment.text = data.department
            binding.tvLevel.text = data.level
           // binding.profileImage1.getBitmap() = data.profilePhoto


        })
    }
}
