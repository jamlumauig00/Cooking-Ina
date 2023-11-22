package com.jam.cookingina

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.jam.cookingina.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("data")
        Log.e("dataintent", data.toString())

        val fragment = FirstFragment()
        val args = Bundle()
        args.putString("data", data)
        fragment.arguments = args

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_second, fragment)
            .commit()
    }
}