package com.nikhil.alertdialogprac

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
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
            intent.setData(Uri.parse("https://www.youtube.com/"))
            startActivity(intent)
        }
        binding1.btnImp2.setOnClickListener {
            var intent=Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:7529073222"))
            startActivity(intent)
        }
        binding1.btnImp3.setOnClickListener {
            val recipient = "Nikhilmahajan8787@gmail.com"
            val subject = "Assignment Submission"
            val body = """
        Dear Sir/Madam,
        
        I have attached my assignment for your review.
        
        Regards,
        Nikhil Mahajan
    """.trimIndent()

            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, recipient)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, body)
            }

            startActivity(emailIntent)
        }
        binding1.btnImp4.setOnClickListener {
            val phoneNumber = "7529073222"
            val message = "Hello, hi"

            val smsIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("smsto:$phoneNumber")
                putExtra("sms_body", message)
            }

            if (smsIntent.resolveActivity(packageManager) != null) {
                startActivity(smsIntent)
            } else {
                println("No SMS client found.")
            }
        }

    }

}