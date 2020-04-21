package com.shajt3ch.todomvvm.view.ui.profile.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

import com.shajt3ch.todomvvm.R
import kotlinx.android.synthetic.main.edit_profile_fragment.*

class EditProfileFragment : Fragment() {

    companion object {
        const val TAG = "EditProfileFragment"
        fun newInstance() = EditProfileFragment()
    }

    private lateinit var viewModel: EditProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditProfileViewModel::class.java)

        //get the ars
        val args = EditProfileFragmentArgs.fromBundle(arguments!!)

        editProfileName.setText(args.name)
        editProfileEmail.setText(args.email)
        editProfileBio.setText(args.bio)

        Glide.with(this)
            .load(args.imageUrl)
            .centerCrop()
            .into(editProfileImage)


    }

}
