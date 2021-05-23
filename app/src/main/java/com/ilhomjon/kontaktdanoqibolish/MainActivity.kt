package com.ilhomjon.kontaktdanoqibolish

import Adapter.RvAdapter
import Models.Contact
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var contactList:ArrayList<Contact>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactList = ArrayList()



        btn.setOnClickListener {
            val contacts = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null)
            while (contacts!!.moveToNext()){
                val contact = Contact(
                    contacts!!.getString(contacts!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    contacts!!.getString(contacts!!.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                )
                val rasmUrl = contacts!!.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

                if (rasmUrl != null){
                    contact.image = MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(rasmUrl))
                }

                contactList.add(contact)
            }
            contacts!!.close()

            rv.adapter = RvAdapter(contactList)
        }
    }
}