package com.nikhil.alertdialogprac

import android.app.Dialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.databinding.ActivityCustomAs2Binding
import com.nikhil.alertdialogprac.databinding.ActivityLisstBinding
import java.util.ArrayList

class LisstActivity : AppCompatActivity() {
    lateinit var binding: ActivityLisstBinding
    lateinit var adapter: ArrayAdapter<String>
    val items= arrayListOf("doctor","engineer","lawyer","CA")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLisstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = ArrayAdapter(this, android.R.layout.test_list_item, items)
        binding.list1.adapter=adapter


        binding.list1.setOnItemClickListener { parent, view, position, id ->
            var dialog1= Dialog(this)
            var dialog1binding=ActivityCustomAs2Binding.inflate(layoutInflater)
            dialog1.setContentView(dialog1binding.root)
            dialog1binding.etListUpdate.setText(items[position])
            dialog1.show()
            dialog1.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog1binding.btnListUpdate.setOnClickListener {
                val up = dialog1binding.etListUpdate.text.toString()
                if (up.isNotEmpty()) {
                    items[position] = up
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this@LisstActivity, "Item updated: $up", Toast.LENGTH_SHORT).show()
                    dialog1.dismiss()
                } else {
                    Toast.makeText(this@LisstActivity, "Enter a valid item!", Toast.LENGTH_SHORT).show()
                }
            }


        }
        binding.list1.setOnItemLongClickListener { parent, view, position, id ->
            var aldialog=AlertDialog.Builder(this).apply{
                setTitle("Do you Want to delete this item?")
                setNegativeButton("No"){_,_ ->

                }
                setPositiveButton("YES"){_,_ ->
                    val deleted=items.removeAt(position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this@LisstActivity,"Item deleted:$deleted",Toast.LENGTH_SHORT).show()
                }

                setCancelable(false)
                show()

            }

            return@setOnItemLongClickListener true

        }
        binding.btnListadd.setOnClickListener {
            var add=binding.etAdd.text.toString()
            if(add.isNullOrEmpty()) {
                Toast.makeText(this@LisstActivity,"Add something first",Toast.LENGTH_SHORT).show()
            }
            else
                items.add(add)
            adapter.notifyDataSetChanged()
            Toast.makeText(this@LisstActivity, "items added in list $add", Toast.LENGTH_SHORT)
                .show()
        }
    }
}