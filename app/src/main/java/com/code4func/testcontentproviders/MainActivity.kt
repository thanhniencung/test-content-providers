package com.code4func.testcontentproviders

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.UserDictionary
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

private const val authority = "com.code4func.helloandroid"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        val uri = Uri.parse("content://$authority/user")

        // insert
        val values = ContentValues()
        values.put("name", "alice11111")
        values.put("email", "alice1111@gmail.com");
        contentResolver.insert(uri, values)


        // select
        val projection = arrayOf("name", "email")

        val cursor = contentResolver.query(
                uri, projection,
                null, null, null
        )!!

        val stringBuilder = StringBuilder()
        try {
            if (cursor.moveToFirst()) {
                val nameIndex = cursor.getColumnIndex("name")
                val emailIndex = cursor.getColumnIndex("email")

                do {
                    val name = cursor.getString(nameIndex)
                    val email = cursor.getString(emailIndex)

                    stringBuilder.append("$name - $email \n")
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {

        } finally {
            cursor.close()
        }

        tvResult.text = stringBuilder.toString()

    }
}
