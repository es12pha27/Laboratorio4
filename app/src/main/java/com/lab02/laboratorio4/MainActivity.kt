
package com.lab02.laboratorio4
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit  var box1:AppCompatEditText
    lateinit  var box2:AppCompatEditText
    lateinit  var box3:AppCompatEditText
    lateinit  var box4:AppCompatEditText
    lateinit  var box5:AppCompatEditText
    lateinit  var box6:AppCompatEditText
    lateinit var  tag1: AppCompatTextView
    lateinit var  tag2:AppCompatTextView
    lateinit var  tag3:AppCompatTextView
    lateinit var  tag4:AppCompatTextView
    lateinit var  tag5:AppCompatTextView
    lateinit var  tag6:AppCompatTextView
    lateinit var  saveConf:Button
    lateinit  var sizet:AppCompatEditText
    lateinit  var colort:AppCompatEditText
    lateinit  var stylet:AppCompatEditText
    lateinit var counterManager: CounterDataStoreManager
    lateinit var back:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //UI
        back = findViewById(R.id.backall)
        box1 = findViewById(R.id.box1)
        box2 = findViewById(R.id.box2)
        box3 = findViewById(R.id.box3)
        box4 = findViewById(R.id.box4)
        box5 = findViewById(R.id.box5)
        box6 = findViewById(R.id.box6)

        tag1 = findViewById(R.id.tag1)
        tag2 = findViewById(R.id.tag2)
        tag3 = findViewById(R.id.tag3)
        tag4 = findViewById(R.id.tag4)
        tag5 = findViewById(R.id.tag5)
        tag6 = findViewById(R.id.tag6)

        saveConf = findViewById(R.id.save_config)

        colort = findViewById(R.id.color)
        sizet = findViewById(R.id.size)
        stylet = findViewById(R.id.style)


        counterManager = CounterDataStoreManager(this)

        // Collect the counter value and set the text everytime the value changes
        lifecycleScope.launch {
            counterManager.counter.collect { counter ->
                tag1.setTextSize(counter.toFloat())
                tag2.setTextSize(counter.toFloat())
                tag3.setTextSize(counter.toFloat())
                tag4.setTextSize(counter.toFloat())
                tag5.setTextSize(counter.toFloat())
                tag6.setTextSize(counter.toFloat())
                box1.setTextSize(counter.toFloat())
                box2.setTextSize(counter.toFloat())
                box3.setTextSize(counter.toFloat())
                box4.setTextSize(counter.toFloat())
                box5.setTextSize(counter.toFloat())
                box6.setTextSize(counter.toFloat())

            }
            counterManager.color.collect { color ->
                if (color == 0) {
                    back.setBackgroundColor(Color.parseColor("#ffffff"))
                }
                if (color == 1) {
                    back.setBackgroundColor(Color.parseColor("#0000ff"))
                }
                if (color == 2) {
                    back.setBackgroundColor(Color.parseColor("#008000"))
                }
            }

        }
        saveConf.setOnClickListener {
              var c=colort.text.toString()
            var nc=0
            if(c.equals("white")){
                nc=0
            }
            if(c.equals("blue")){
                nc=1
            }
            if(c.equals("green")){
                nc=2
            }
            lifecycleScope.launch {
                counterManager.setCounter(sizet.text.toString().toInt())
                counterManager.setColor(nc)
            }

            }
        }














}