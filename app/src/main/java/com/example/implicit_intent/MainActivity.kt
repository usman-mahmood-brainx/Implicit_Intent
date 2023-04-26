package com.example.implicit_intent

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendEmailButton = findViewById<Button>(R.id.sendEmailButton)
        sendEmailButton.setOnClickListener{sendmail()}

        val secondActivityButton = findViewById<Button>(R.id.SecondActivity)
        secondActivityButton.setOnClickListener{
            val intent = Intent(this,ChoosePictureActivity::class.java)
            intent.putExtra("Button","Take a Picture")
            startActivity(intent)
        }


//        sendEmailButton.setOnClickListener {
//            val intent = Intent(Intent.ACTION_SENDTO).apply {
//                data = Uri.parse("mailto:example@example.com")
//                putExtra(Intent.EXTRA_SUBJECT, "Subject")
//                putExtra(Intent.EXTRA_TEXT, "Body")
//            }
//
//            val chooserIntent = Intent.createChooser(intent, "Send Email")
//            if (intent.resolveActivity(packageManager) != null) {
//                startActivity(chooserIntent)
//            }
//        }

    }

    fun sendmail(){
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:example@example.com")
            putExtra(Intent.EXTRA_SUBJECT, "Subject")
            putExtra(Intent.EXTRA_TEXT, "Body")
//            `package` = "com.google.android.gm"
        }

        val chooserIntent = Intent.createChooser(intent, "Send Email")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(chooserIntent)
        }
    }
}