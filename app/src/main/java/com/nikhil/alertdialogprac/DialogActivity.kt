package com.nikhil.alertdialogprac

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.databinding.ActivityCustomAsBinding
import com.nikhil.alertdialogprac.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity() {
    lateinit var binding1: ActivityDialogBinding
    private var currentResult = 0//giving default initiliazation
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding1 = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding1.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //connection result to edit text field
        binding1.etnummain.setText(currentResult.toString())
        //calling a custom dialog
        binding1.btnAdd.setOnClickListener {
            var dialog = Dialog(this)
            //binding on custom dialog
            val dialogBinding = ActivityCustomAsBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)
            dialog.show()
            //setting width and height
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            //add button
            dialogBinding.btnAlertadd.setOnClickListener {
                //takes strring and converts to integer
                val dialogValue = dialogBinding.etnumber.text.toString().toIntOrNull() ?: 0
                currentResult += dialogValue
                binding1.etnummain.setText(currentResult.toString())
                dialog.dismiss()
            }
            //sub button
            dialogBinding.btnAlertsub.setOnClickListener {
                val dialogValue = dialogBinding.etnumber.text.toString().toIntOrNull() ?: 0
                currentResult -= dialogValue
                binding1.etnummain.setText(currentResult.toString())
                dialog.dismiss()
            }

            // Reset Button Logic
            dialogBinding.btnAlertreset.setOnClickListener {
                currentResult = 0
                binding1.etnummain.setText(currentResult.toString())
                dialog.dismiss()
                Toast.makeText(this, "Values Reset to 0", Toast.LENGTH_SHORT).show()
            }

        }
    }
}