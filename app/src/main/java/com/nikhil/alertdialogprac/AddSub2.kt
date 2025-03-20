package com.nikhil.alertdialogprac

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.databinding.ActivityAddSub2Binding

class AddSub2 : AppCompatActivity() {
    lateinit var binding: ActivityAddSub2Binding
    private var result=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityAddSub2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.n.setText(result.toString())
        binding.addcalc.setOnClickListener {
            val value=binding.n.text.toString().toIntOrNull()?:0
            result=value+2
            binding.n.setText(result.toString())
        }
        binding.subcalc.setOnClickListener {
            val value=binding.n.text.toString().toIntOrNull()?:0
            result=value-2
            binding.n.setText(result.toString())
        }
        binding.divcalc.setOnClickListener {
            val value=binding.n.text.toString().toIntOrNull()?:0
            result=value/2
            binding.n.setText(result.toString())
        }
        binding.multcalc.setOnClickListener {
            val value=binding.n.text.toString().toIntOrNull()?:0
            result=value*2
            binding.n.setText(result.toString())
        }
    }
}