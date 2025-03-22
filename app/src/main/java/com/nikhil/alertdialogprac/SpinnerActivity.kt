package com.nikhil.alertdialogprac

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.databinding.ActivitySpinnerBinding

class SpinnerActivity : AppCompatActivity() {
    lateinit var binding: ActivitySpinnerBinding
    lateinit var spinner:Spinner
    lateinit var adapter: ArrayAdapter<String>
    val items= mutableListOf("doctor","engineer","lawyer","CA")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner=binding.spin
        spinner.adapter = adapter
        fun getposition(): Int? {
            val positionstr = binding.position.text.toString().trim()
            return if (positionstr.isNotEmpty() && positionstr.toIntOrNull() != null) {
                val pos = positionstr.toInt()
                if (pos in 0..items.size) pos else null
            } else null
        }


        fun getitem(): String? {
            val itemStr = binding.value.text.toString().trim()
            return if (itemStr.isNotEmpty()) itemStr else null
        }
        binding.addspin.setOnClickListener {
            val position=getposition()
            val item=getitem()
            if (position != null && item != null) {
                items.add(position, item)
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"Item inserted",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid Position or Empty Item!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.deletespin.setOnClickListener {
            val position = getposition()
            if (position != null && position < items.size)
            {
                val removedItem = items.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"Item deleted",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Invalid Position or Out of Range!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.updatespin.setOnClickListener {
            val position=getposition()
            val upitem=getitem()
            if(position!=null && upitem !=null && position<items.size)
            {
                items[position]=upitem
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"Item updated",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"invalid",Toast.LENGTH_SHORT).show()
            }
        }

    }
}