package com.example.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable


class MainActivity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var process = ""
        var checkBracket = false;

        var btn0 = findViewById<Button>(R.id.btnNum0);
        var btn1 = findViewById<Button>(R.id.btnNum1);
        var btn2 = findViewById<Button>(R.id.btnNum2);
        var btn3 = findViewById<Button>(R.id.btnNum3);
        var btn4 = findViewById<Button>(R.id.btnNum4);
        var btn5 = findViewById<Button>(R.id.btnNum5);
        var btn6 = findViewById<Button>(R.id.btnNum6);
        var btn7 = findViewById<Button>(R.id.btnNum7);
        var btn8 = findViewById<Button>(R.id.btnNum8);
        var btn9 = findViewById<Button>(R.id.btnNum9);
        var btnTitik = findViewById<Button>(R.id.btnTitik);
        var btnDel = findViewById<Button>(R.id.btnHapus);

        var btnPlus = findViewById<Button>(R.id.btnPenjumlahan);
        var btnMin = findViewById<Button>(R.id.btnPengurangan);
        var btnBagi = findViewById<Button>(R.id.btnPembagian);
        var btnKali = findViewById<Button>(R.id.btnPerkalian);
        var btnSamaDengan = findViewById<Button>(R.id.btnSamaDengan);

        var btnPersen = findViewById<Button>(R.id.btnPersen);
        var btnKurung = findViewById<Button>(R.id.btnKurung);
        var btnClear = findViewById<Button>(R.id.btnClear);

        var tvAtas = findViewById<EditText>(R.id.txtTop)
        var tvBawah = findViewById<TextView>(R.id.txtButtom)

        tvAtas.showSoftInputOnFocus = false
        fun moveCursor(editText: EditText){
            editText.setSelection(editText.text.length)
        }

        btnClear.setOnClickListener{
            tvAtas.setText("")
            tvBawah.setText("")
        }

        btn0.setOnClickListener {
            process = tvAtas.text.toString();
            tvAtas.setText(process + "0");
            moveCursor(tvAtas)
        }
        btn1.setOnClickListener{
            process = tvAtas.text.toString()
            tvAtas.setText(process + "1")
            moveCursor(tvAtas)
        }
        btn2.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "2")
            moveCursor(tvAtas)
        }
        btn3.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "3")
            moveCursor(tvAtas)
        }
        btn4.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "4")
            moveCursor(tvAtas)
        }
        btn5.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "5")
            moveCursor(tvAtas)
        }
        btn6.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "6")
            moveCursor(tvAtas)
        }
        btn7.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "7")
            moveCursor(tvAtas)
        }
        btn8.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "8")
            moveCursor(tvAtas)
        }
        btn9.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "9")
            moveCursor(tvAtas)
        }

        btnPlus.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "+")
            moveCursor(tvAtas)
        }
        btnKali.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "×")
            moveCursor(tvAtas)
        }
        btnBagi.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "÷")
            moveCursor(tvAtas)
        }
        btnMin.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "-")
            moveCursor(tvAtas)
        }
        btnTitik.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + ".")
            moveCursor(tvAtas)
        }
        btnPersen.setOnClickListener {
            process = tvAtas.text.toString()
            tvAtas.setText(process + "%")
            moveCursor(tvAtas)
        }
        btnKurung.setOnClickListener {
            if (checkBracket){
                process = tvAtas.text.toString()
                tvAtas.setText(process + ")")
                checkBracket = false
                moveCursor(tvAtas)
            }
            else{
                process = tvAtas.text.toString()
                tvAtas.setText(process + "(")
                checkBracket = true
                moveCursor(tvAtas)
            }
        }
        btnDel.setOnClickListener {
            val currentText = tvAtas.text.toString()
            if (currentText.isNotEmpty()){
                val newText = currentText.substring(0, currentText.length - 1)
                tvAtas.setText(newText)
                moveCursor(tvAtas)
            }
        }
        btnSamaDengan.setOnClickListener {
            process = tvAtas.text.toString()

            process = process.replace("×","*")
            process = process.replace("÷","/")
            process = process.replace("%","/100")

            val rhino:Context = Context.enter()
            rhino.optimizationLevel = -1
            var finalResult = ""

            finalResult = try {
                val scriptable:Scriptable = rhino.initStandardObjects()
                rhino.evaluateString(scriptable,process,"javascript",1,null).toString()
            }catch (e:Exception){
                "Erorr"
            }
            tvAtas.setText(finalResult)
            tvBawah.setText("")
            moveCursor(tvAtas)
        }
        tvAtas.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {
                process = tvAtas.text.toString()
                process = process.replace("×","*")
                process = process.replace("÷","/")
                process = process.replace("%","/100")
                val rhino:Context = Context.enter()
                rhino.optimizationLevel = -1
                var finalResult = ""
                finalResult = try {
                    val scriptable:Scriptable = rhino.initStandardObjects()
                    rhino.evaluateString(scriptable,process,"javascript",1,null).toString()
                }catch (e:Exception){
                    "Erorr"
                }finally {
                    Context.exit()
                }
                tvBawah.text = finalResult
            }
        })
    }
}

