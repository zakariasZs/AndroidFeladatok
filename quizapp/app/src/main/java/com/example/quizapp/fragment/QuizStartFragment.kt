package com.example.quizapp.fragment

import android.content.Intent
import android.database.Cursor
import android.drm.DrmStore.DrmObjectType.CONTENT
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.SecondActivity
import com.example.quizapp.databinding.QuizStartBinding
import com.google.android.material.snackbar.Snackbar
import com.example.quizapp.viewModel.UserViewModel as UserViewModel

class QuizStartFragment : Fragment(R.layout.quiz_start) {

    private lateinit var binding: QuizStartBinding
    private val PICK_CONTACT_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //without binding
//        return inflater.inflate(R.layout.quiz_start, container, false)
        //with binding
        binding = QuizStartBinding.inflate(inflater)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model : UserViewModel by requireActivity().viewModels()

        binding.button.setOnClickListener{
            val textField: EditText = binding.editTextTextPersonName
            val textFieldText = textField.text
            model.userName = textFieldText.toString()
            this.findNavController().navigate(R.id.quizFragment)


        }

        binding.button2.setOnClickListener{
        Snackbar.make(binding.button2, "Text label", Snackbar.LENGTH_LONG)
            .setAction("Action") {
                val toast = Toast.makeText(getActivity(), "Action pressed", Toast.LENGTH_SHORT)
                toast.show()
            }
            .show()
        }
    }

//    private fun pickContact() {
//        val pickContact = Intent(Intent.ACTION_PICK)
//        pickContact.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
//        startActivityForResult(pickContact, PICK_CONTACT_REQUEST_CODE)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        val model : UserViewModel by requireActivity().viewModels()
//        if (requestCode == PICK_CONTACT_REQUEST_CODE && data != null) {
//            val contactData: Uri? = data.data
//            if (contactData != null) {
//                val cursor: Cursor? = requireActivity().contentResolver.query(contactData, null, null, null, null)
//                if (cursor != null && cursor.moveToFirst()) {
//                    val nameIndex: Int =
//                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
//                    val name: String = cursor.getString(nameIndex)
//                    cursor.close()
//                    val intent = Intent(requireActivity(), SecondActivity::class.java)
//                    model.userName = name
//                    startActivity(intent)
//                }
//            }
//        }
//    }


}