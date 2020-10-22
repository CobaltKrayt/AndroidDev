package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //this is a way in which we can bind all our views from a single layout so that we get their ids at compiletime not at runtime
    //it also eliminates the need to have a findmyview function as you use the views as calls from the binding object
    private lateinit var binding: ActivityMainBinding

    //we make a MyName instance called myName... so dumb... changed the class to MyNames so I understand this better
    private val Name: MyNames = MyNames("George")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)//use this if we use only viewbinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //we are setting myName from the xml to the variable Name instatiated above
        binding.myName = Name

        //we get the button and use the addNickname function in setonclicklistener
        //findViewById<Button>(R.id.done_button).setOnClickListener(){
        //     addNickname(it)
        //}

        binding.doneButton.setOnClickListener(){
            addNickname(it)
        }

    }

    //we pass the visibility/view property of the button
    private fun addNickname(view: View)
    {
        //VIEW BINDING WAY
        /*
        //we take the ids from the edit and text field
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickanme_text)

        //we exchange the text
        nicknameTextView.text = editText.text

        //we make the field and the button invisible
        editText.visibility = View.GONE
        view.visibility = View.GONE
        //we show the nickname_text field
        nicknameTextView.visibility = View.VISIBLE

        */

        binding.apply{

            myName?.nickname = nicknameEdit.text.toString()
            // Invalidate all binding expressions and request a new rebind to refresh UI
            //does it invalidate the ones under or what dafuq does this do and why does it do that?
            //refreshes ui by asking the system to look for the bindings cause i changed one binding on line 64?
            invalidateAll()

            nicknameText.text = nicknameEdit.text
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }


        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }

}