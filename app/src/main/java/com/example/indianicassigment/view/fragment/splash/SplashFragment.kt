package com.example.indianicassigment.view.fragment.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.indianicassigment.BR
import com.example.indianicassigment.R
import com.example.indianicassigment.data.local.prefs.SharedPrefsHelper
import com.example.indianicassigment.databinding.FragmentSplashBinding
import com.example.indianicassigment.utils.DBConstants
import com.example.indianicassigment.utils.Util
import com.example.indianicassigment.view.activity.main.MainActivity
import com.example.indianicassigment.view.base.BaseFragment
import javax.inject.Inject

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(),
    SplashNavigator {
    @Inject
    protected lateinit var mViewModel: SplashViewModel
    private lateinit var binding: FragmentSplashBinding

    override fun getViewModel() = mViewModel
    override fun getBindingVariable() = BR.splashFrgViewModel
    override fun getLayoutId() = R.layout.fragment_splash
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val changeObserver = Observer<String> { value ->
        value?.let {
            if (mViewModel.mDataManager.getBooleanData(DBConstants.IS_LOGGED_IN) == true){
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }else {
                openLoginFragment()
            }


        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewBinding()
        mViewModel.setNavigator(this)
        val tempLang = mViewModel.mDataManager.getStringData(SharedPrefsHelper.LANGUAGE)
        if (tempLang == "") {
            mViewModel.mDataManager.setStringData(SharedPrefsHelper.LANGUAGE, "en-US")
        }

        mViewModel.updateProfilePic().observe(viewLifecycleOwner, changeObserver)
    }



    override fun isError(errorTitle: String, errorMessage: String) {
        Util.hideProgress()
    }

    override fun isError(error: String) {
        Util.hideProgress()
    }

    override fun isSuccess(tag: Int,message :String) {
        Util.hideProgress()
    }
    override fun getHeaderView(): View {
        return view?.findViewById(R.id.header) as View
    }
    override fun isHeader(): Boolean {
        return false
    }

    override fun isBack(): Boolean {
        return false
    }

    override fun isDrawer(): Boolean {
        return false
    }

    override fun getTitle(): String {
        return getString(R.string.app_name)
    }
    override fun onDrawerClick(): View.OnClickListener {
        return View.OnClickListener {  }
    }
    override fun openLoginFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }
  }