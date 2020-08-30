package com.decimalab.easytask.view.ui.darkmode

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.decimalab.easytask.BuildConfig
import com.decimalab.easytask.R
import com.decimalab.easytask.model.local.AppPreferences
import com.decimalab.easytask.util.AppThemeMode
import kotlinx.android.synthetic.main.dark_mode_fragment.*

class DarkModeFragment : DialogFragment(), RadioGroup.OnCheckedChangeListener {
    companion object {
        const val TAG = "DarkModeFragment"
    }

    private lateinit var appPreferences: AppPreferences
    private lateinit var sharedPreferences: SharedPreferences

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dark_mode_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //pref
        sharedPreferences =
            requireActivity().getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE)
        appPreferences = AppPreferences(sharedPreferences)
        //group check change listener
        groupMode.setOnCheckedChangeListener(this)

        //check android version
        if (isPreAndroidTen()) {
            system.text = getString(R.string.battery_mode)
        }

        //get theme mode
        getThemMode()
    }

    private fun getThemMode() {
        when (appPreferences.getThemeMode()) {
            AppThemeMode.LIGHT.ordinal -> light.isChecked = true
            AppThemeMode.DARK.ordinal -> dark.isChecked = true
            AppThemeMode.SYSTEM.ordinal, AppThemeMode.BATTERY.ordinal -> system.isChecked = true
            else -> system.isChecked = true
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.light -> {
                setThemeMode(AppCompatDelegate.MODE_NIGHT_NO, AppThemeMode.LIGHT)
            }
            R.id.dark -> {
                setThemeMode(AppCompatDelegate.MODE_NIGHT_YES, AppThemeMode.DARK)
            }
            R.id.system -> {
                if (isPreAndroidTen()) {
                    setThemeMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, AppThemeMode.BATTERY)
                } else {
                    setThemeMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, AppThemeMode.SYSTEM)
                }
            }
        }
    }

    private fun setThemeMode(mode: Int, themeMode: AppThemeMode) {
        AppCompatDelegate.setDefaultNightMode(mode)
        appPreferences.setThemeMode(themeMode.ordinal)

    }

    private fun isPreAndroidTen() = Build.VERSION.SDK_INT < Build.VERSION_CODES.Q


}
