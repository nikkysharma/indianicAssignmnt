package com.example.indianicassigment.view.activity.auth

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.indianicassigment.BR


import com.example.indianicassigment.view.base.BaseActivity



import javax.inject.Inject


import com.example.indianicassigment.R
import com.example.indianicassigment.databinding.ActivityAuthBinding


class AuthActivity : BaseActivity<ActivityAuthBinding, AuthActivityViewModel>()
        , AuthNavigator {

    @Inject
    protected lateinit var viewModel: AuthActivityViewModel
    private var navControllerActivity: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    fun init(){
        viewModel.setNavigator(this)
        //startLocationUpdates()
        navControllerActivity = Navigation.findNavController(this, R.id.nav_host_fragment)
    }

    override fun getBindingVariable() = BR.authViewModel
    override fun getMyViewModel() = viewModel
    override fun getLayoutId()= R.layout.activity_auth




}
