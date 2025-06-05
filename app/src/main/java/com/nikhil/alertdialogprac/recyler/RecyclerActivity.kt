package com.nikhil.alertdialogprac.recyler

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.alertdialogprac.R
import com.nikhil.alertdialogprac.databinding.ActivityCustomuplistBinding
import com.nikhil.alertdialogprac.databinding.ActivityRecyclerAddBinding
import com.nikhil.alertdialogprac.databinding.ActivityRecyclerBinding
import com.nikhil.alertdialogprac.databinding.ActivityRecyclerUpdateBinding
import com.nikhil.alertdialogprac.listpackage.Inter
import com.nikhil.alertdialogprac.listpackage.student

class RecyclerActivity : AppCompatActivity(),Inter {
    lateinit var binding: ActivityRecyclerBinding
    lateinit var recyclerAdapter: RecyclerAdapter
    var array = arrayListOf<student>()
    var Noteslist = arrayListOf<User>()
    lateinit var notesDatabase: NotesDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notesDatabase = NotesDatabase.getInstance(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        notesDatabase.DAO().insertTodo(User(title = "test New", description = "C++"))
        array.add(student("Test Name", "20", "KOTLIN"))
        array.add(student("Name1", "22", "C"))
        array.add(student("New", "12", "C++"))
        array.add(student("name45", "6", "Java"))
        recyclerAdapter = RecyclerAdapter(array,this)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = recyclerAdapter

        binding.btnRecycleadd.setOnClickListener {
            val dialog = Dialog(this)
            val dialogBinding = ActivityRecyclerAddBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)

            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            dialog.show()
            dialogBinding.btnReadditeminlist.setOnClickListener {
                val name = dialogBinding.etaddrename.text.toString().trim()
                val rollNo = dialogBinding.etaddrerollno.text.toString().trim()
                val subject = dialogBinding.etaddrelanguage.text.toString().trim()

                if (name.isNotEmpty() && rollNo.isNotEmpty() && subject.isNotEmpty()) {
                    array.add(student(name, rollNo, subject))
                    recyclerAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "Item added: $name", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onitemclick(position: Int) {
        var dialog3= Dialog(this)
        var dialog3binding= ActivityRecyclerUpdateBinding.inflate(layoutInflater)
        dialog3.setContentView(dialog3binding.root)
        val selectedStudent = array[position]
        dialog3binding.etreupname.setText(selectedStudent.name)
        dialog3binding.etreuprollno.setText(selectedStudent.rollNo)
        dialog3binding.etreuplanguage.setText(selectedStudent.subject)
        dialog3.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog3.show()
        dialog3binding.btnReupdateiteminlist.setOnClickListener {
            val up = dialog3binding.etreupname.text.toString()
            val up1 = dialog3binding.etreuprollno.text.toString()
            val up2 = dialog3binding.etreuplanguage.text.toString()
            if (up.isNotEmpty()||up1.isNotEmpty()||up2.isNotEmpty()) {
                array[position] = student(up, up1, up2)
                recyclerAdapter.notifyDataSetChanged()
                Toast.makeText(this@RecyclerActivity, "Item updated: $up", Toast.LENGTH_SHORT).show()
                dialog3.dismiss()
            } else {
                Toast.makeText(this@RecyclerActivity, "Enter a valid item!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onitemlong(position: Int) {
        var aldialog = AlertDialog.Builder(this).apply {
            setTitle("Do you Want to delete this item?")
            setNegativeButton("No") { _, _ ->

            }
            setPositiveButton("YES") { _, _ ->
                notesDatabase.DAO().deleteTodoEntity(Noteslist[position])
                getList()
                recyclerAdapter.notifyDataSetChanged()
                Toast.makeText(this@RecyclerActivity, "Item deleted", Toast.LENGTH_SHORT)
                    .show()
            }

            setCancelable(false)
            show()
        }
    }
    private fun getList() {
        Noteslist.clear()
        Noteslist.addAll(notesDatabase.DAO().getList())
    }
}