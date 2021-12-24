package com.example.timetableapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import androidx.lifecycle.ViewModelProvider
import com.example.timetableapp.databinding.ActivitySetUpPageBinding
import com.example.timetableapp.model.SetupUser
import com.example.timetableapp.viewmodel.SetupUserViewModel
import com.github.drjacky.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_set_up_page.*
import kotlinx.android.synthetic.main.activity_set_up_page.view.*

class SetUpPage : AppCompatActivity() {

    private lateinit var binding: ActivitySetUpPageBinding

    private lateinit var mSetupUserViewModel: SetupUserViewModel

    private var imagePicker: ImageView?=null

    lateinit var sharedPreferences: SharedPreferences
    var isRemembered = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPreferences = getSharedPreferences( "SHARED_PREF", Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean( "SAVED", true)

        if (isRemembered) {
            val intent = Intent( this, MainScreen::class.java)
            startActivity(intent)
            finish()
        }


        getSupportActionBar()?.hide()

        mSetupUserViewModel = ViewModelProvider(this).get(SetupUserViewModel::class.java)


        imagePicker = binding.profileImage
        val gallery =  binding.cameraButton
        binding.cameraButton.setOnClickListener {

            ImagePicker.with(this).galleryOnly().galleryMimeTypes(arrayOf("image/*")).crop().maxResultSize(400, 400).start()
        }


        binding.continueBotton.setOnClickListener {
            val id = 0
            val institution = binding.etInstitution.text.toString()
            val name = binding.etName.text.toString()
            val department = binding.etDepartment.text.toString()
            val level = binding.etLevel.text.toString()
            //val profilePhoto = binding.cameraButton.drawToBitmap()


            if(inputCheck(institution, name, department, level)){
                // Create User Object
                    val setupUser = SetupUser(id, institution, name, department, level)

                // Add Data to Database
                mSetupUserViewModel.addUser(setupUser)
                Toast.makeText(this, "Successfully added", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, MainScreen::class.java))
                finish()
            } else{
                Toast.makeText(this, "Please fill out all field", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun inputCheck(institution: String, name: String, department: String, level: String): Boolean{
        return !(TextUtils.isEmpty(institution) && TextUtils.isEmpty(name) && TextUtils.isEmpty(department) && level.isEmpty())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode== Activity.RESULT_OK && requestCode== ImagePicker.REQUEST_CODE) {


            imagePicker?.setImageURI(data?.data)
        }

    }
}
