package com.nikhil.alertdialogprac

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.databinding.ActivityImplicitAct2Binding

class ImplicitAct2 : AppCompatActivity() {
    lateinit var binding1: ActivityImplicitAct2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding1=ActivityImplicitAct2Binding.inflate(layoutInflater)
        setContentView(binding1.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding1.btnImp1.setOnClickListener {
            var intent=Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(""))
            startActivity(intent)
        }
        binding1.btnImp2.setOnClickListener {
            var intent=Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:7529073222"))
            startActivity(intent)
        }
       binding1.btnImp3.setOnClickListener {
           startActivity(Intent(Intent.ACTION_SENDTO).setData(Uri.parse("mailto:")))
       }
    }
}