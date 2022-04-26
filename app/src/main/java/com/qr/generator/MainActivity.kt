package com.qr.generator

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import android.os.Bundle
import android.graphics.Bitmap
import android.graphics.Color
import android.view.View
import android.util.Log
import android.widget.Toast
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException

private const val TAG = "MainActivity"

public class MainActivity : AppCompatActivity() {

  val text = "null"
  private lateinit var b1: MaterialButton
  private lateinit var e1: TextInputEditText
  private lateinit var i1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)

     b1 = findViewById(R.id.b1) as MaterialButton
     e1 = findViewById(R.id.e1) as TextInputEditText
     i1 = findViewById(R.id.i1) as ImageView

      b1.setOnClickListener{
      val text = e1.text.toString().trim()
       if(text.trim().isEmpty()){

        Toast.makeText(this,"Empty",3000).show()
       
	   }else{

        val bitmap = generateQRCode(text.trim())
        i1.setImageBitmap(bitmap)
       }
    }
}

private fun generateQRCode(text: String): Bitmap {
   val width = 150
   val height = 150
   val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
   val codeWriter = MultiFormatWriter()
   try {
     val bitMatrix =
       codeWriter.encode(text, BarcodeFormat.QR_CODE, width, height)
         for (x in 0 until width) {
          for (y in 0 until height) {
           val color = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
            bitmap.setPixel(x, y, color)
       }
   }
} catch (e: WriterException) {
	
Log.d(TAG, "generateQRCode: ${e.message}")

      }
     return bitmap
  }
}