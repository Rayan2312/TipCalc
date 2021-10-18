package edu.stanford.ralbraid.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import kotlin.math.ceil
import kotlin.math.floor

private const val TAG = "MainActivity"
private const val DEFAULT_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvTipPercent: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private lateinit var Round_Up: Button
    private lateinit var Round_Down: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercent = findViewById(R.id.tvPercentageLabel)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        Round_Down = findViewById(R.id.roundDownButton)
        Round_Up = findViewById(R.id.roundUpButton)

        seekBarTip.progress = DEFAULT_TIP_PERCENT
        tvTipPercent.text = "$DEFAULT_TIP_PERCENT%"
        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //Log.i(TAG, "on Progress Changed $progress")
                tvTipPercent.text = "$progress%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        etBaseAmount.addTextChangedListener(object:  TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                //Log.i(TAG, "afterTextChanged $s")
                computeTipAndTotal()
            }


        })
        Round_Up.setOnClickListener {
         roundUp()
        }
        Round_Down.setOnClickListener{
            roundDown()
        }

    }
    fun roundUp(){
     val temp = ceil(tvTotalAmount.text.toString().toDouble())
        tvTotalAmount.text = temp.toString()
    }
    fun roundDown(){
        tvTotalAmount.text = floor(tvTotalAmount.text.toString().toDouble()).toString()
    }
    private fun computeTipAndTotal() {
        val BaseAmount = etBaseAmount.text.toString().toDouble()
        val tipPercent = seekBarTip.progress
        val tipAmount = BaseAmount * tipPercent/100
        val TotalAmount = BaseAmount + tipAmount
        tvTipAmount.text = tipAmount.toString()
        tvTotalAmount.text = TotalAmount.toString()
    }
}