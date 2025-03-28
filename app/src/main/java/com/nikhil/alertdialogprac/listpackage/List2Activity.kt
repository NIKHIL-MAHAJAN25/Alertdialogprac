package com.nikhil.alertdialogprac.listpackage

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.R
import com.nikhil.alertdialogprac.databinding.ActivityCustomuplistBinding
import com.nikhil.alertdialogprac.databinding.ActivityList2Binding

class List2Activity : AppCompatActivity(),Inter {
    lateinit var binding: ActivityList2Binding
    lateinit var listadap: ListBaseAdapter
    var array = arrayListOf<student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityList2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        array.add(student("Test Name", "20", "KOTLIN"))
        array.add(student("Name1", "22", "C"))
        array.add(student("New", "12", "C++"))
        array.add(student("name45", "6", "Java"))
        listadap = ListBaseAdapter(array, this)
        binding.listLview.adapter = listadap
        listadap.notifyDataSetChanged()

        binding.listAdapadd.setOnClickListener {
            var add=binding.etaddname.text.toString()
            var add1=binding.etAddRoll.text.toString()
            var add2=binding.etAddLang.text.toString()
            if(add.isNullOrEmpty()||add1.isNullOrEmpty()||add2.isNullOrEmpty()) {
                Toast.makeText(this@List2Activity,"Add something first",Toast.LENGTH_SHORT).show()
            }
            else
                array.add(student(add,add1,add2))
            listadap.notifyDataSetChanged()
            Toast.makeText(this@List2Activity, "items added in list $add", Toast.LENGTH_SHORT)
                .show()
            binding.etaddname.text.clear()
            binding.etAddRoll.text.clear()
            binding.etAddLang.text.clear()
        }



    }
    override fun onitemclick(position: Int) {
            var dialog3= Dialog(this)
        var dialog3binding=ActivityCustomuplistBinding.inflate(layoutInflater)
        dialog3.setContentView(dialog3binding.root)
        val selectedStudent = array[position]
        dialog3binding.etupname.setText(selectedStudent.name)
        dialog3binding.etuprollno.setText(selectedStudent.rollNo)
        dialog3binding.etuplanguage.setText(selectedStudent.subject)
        dialog3.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog3.show()
        dialog3binding.btnUpdateiteminlist.setOnClickListener {
            val up = dialog3binding.etupname.text.toString()
            val up1 = dialog3binding.etuprollno.text.toString()
            val up2 = dialog3binding.etuplanguage.text.toString()
            if (up.isNotEmpty()||up1.isNotEmpty()||up2.isNotEmpty()) {
                array[position] = student(up, up1, up2)
                listadap.notifyDataSetChanged()
                Toast.makeText(this@List2Activity, "Item updated: $up", Toast.LENGTH_SHORT).show()
                dialog3.dismiss()
            } else {
                Toast.makeText(this@List2Activity, "Enter a valid item!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onitemlong(position: Int) {
        var aldialog = AlertDialog.Builder(this).apply {
            setTitle("Do you Want to delete this item?")
            setNegativeButton("No") { _, _ ->

            }
            setPositiveButton("YES") { _, _ ->
                val deleted = array.removeAt(position)
                listadap.notifyDataSetChanged()
                Toast.makeText(this@List2Activity, "Item deleted:$deleted", Toast.LENGTH_SHORT)
                    .show()
            }

            setCancelable(false)
            show()

        }
    }



    }
