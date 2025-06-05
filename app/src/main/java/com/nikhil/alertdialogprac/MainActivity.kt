package com.nikhil.alertdialogprac

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri.Builder
import android.os.Bundle
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikhil.alertdialogprac.databinding.ActivityCustomAsBinding
import com.nikhil.alertdialogprac.databinding.ActivityImplicitAct2Binding
import com.nikhil.alertdialogprac.databinding.ActivityMainBinding
import com.nikhil.alertdialogprac.listpackage.List2Activity
import com.nikhil.alertdialogprac.location.LocationActivity
import com.nikhil.alertdialogprac.recyler.RecyclerActivity
import com.nikhil.alertdialogprac.supa.Supabase
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var button: Button? = null

    var ItemsList = arrayOf("Apple", "Banana", "cider")
    var boolArray = booleanArrayOf(true, false, true, true)
    var formatter = SimpleDateFormat("ddd-mm-yyyy")
    var current=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnConst.setOnClickListener {
            startActivity(Intent(this,ConstraintNew::class.java))
        }
        binding.btnAlert.setOnClickListener {
            var aldialog = AlertDialog.Builder(this).apply {
                setTitle("Alert dialog")
                setItems(ItemsList) { dialog, index ->
                    Toast.makeText(this@MainActivity, ItemsList[index], Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("No") { _, _ ->
                    Toast.makeText(this@MainActivity, "Negative Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this@MainActivity, "Positive Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                setNeutralButton("Cancel") { _, _ ->
                    Toast.makeText(this@MainActivity, "Neutral Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                show()
            }

        }
        binding.btnAlert1.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("mutli")
                setMultiChoiceItems(ItemsList, boolArray) { _, index, boolvalue ->
                    boolArray.set(index, boolvalue)
                    Toast.makeText(this@MainActivity, ItemsList[index], Toast.LENGTH_SHORT).show()
                }
                setNegativeButton("No") { _, _ ->
                    Toast.makeText(this@MainActivity, "Negative Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                setPositiveButton("Yes") { _, _ ->
                    Toast.makeText(this@MainActivity, "Positive Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                setNeutralButton("Cancel") { _, _ ->
                    Toast.makeText(this@MainActivity, "Neutral Button Clicked", Toast.LENGTH_SHORT)
                        .show()
                }
                setCancelable(false)
                show()
            }
        }
        binding.btnSnack.setOnClickListener {
            var dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_layout)
            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        binding.btnDate.setOnClickListener {
            var calendar1 = Calendar.getInstance()
            val datePickerDialog =DatePickerDialog(
                this,
                { _, year, month, dayofmonth ->

                    calendar1.set(year, month, dayofmonth)
                    var fromatdate = formatter.format(calendar1.time)
                    binding.btnDate.setText(fromatdate.toString())

                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
            )
            var mincal=Calendar.getInstance()
            mincal.add(Calendar.DAY_OF_MONTH,-10)
            datePickerDialog.datePicker.minDate=mincal.timeInMillis

            val maxCalendar = Calendar.getInstance()
            maxCalendar.add(Calendar.DAY_OF_MONTH, 10)
            datePickerDialog.datePicker.maxDate = maxCalendar.timeInMillis

            datePickerDialog.show()

        }
        binding.btnImp.setOnClickListener {
            startActivity(Intent(this,ImplicitAct2::class.java))
        }
        binding.btnAdsub.setOnClickListener {
            startActivity(Intent(this,DialogActivity::class.java))
        }
        binding.btnAdsub2.setOnClickListener {
            startActivity(Intent(this,AddSub2::class.java))
        }
        binding.btnSpin.setOnClickListener {
            startActivity(Intent(this,SpinnerActivity::class.java))
        }
        binding.btnListview.setOnClickListener {
            startActivity(Intent(this,LisstActivity::class.java))
        }

        binding.btnWebview.setOnClickListener {
            startActivity(Intent(this,WebView::class.java))
        }
        binding.btnListwithbase.setOnClickListener {
            startActivity(Intent(this,List2Activity::class.java))
        }
        binding.btnRecycler.setOnClickListener {
            startActivity(Intent(this,RecyclerActivity::class.java))
        }
        binding.btnLocation.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }
        binding.btnvideo.setOnClickListener {
            startActivity(Intent(this, VideoView::class.java))
        }
        binding.btnvideo2.setOnClickListener {
            startActivity(Intent(this, VideoActivity2::class.java))
        }
        binding.btnvideo3.setOnClickListener {
            startActivity(Intent(this, VideoView3::class.java))
        }
        binding.btnsupa.setOnClickListener {
            startActivity(Intent(this,Supabase::class.java))
        }
    }

}