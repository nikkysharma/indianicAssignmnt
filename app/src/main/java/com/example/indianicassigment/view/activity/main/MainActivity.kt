package com.example.indianicassigment.view.activity.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.indianicassigment.R
import com.example.indianicassigment.databinding.ActivityMainBinding
import com.example.indianicassigment.view.base.BaseActivity
import com.example.indianicassigment.BR
import javax.inject.Inject



class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>()
        , MainNavigator {

    @Inject
    protected lateinit var viewModel: MainActivityViewModel
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

    override fun getBindingVariable() = BR.mainViewModel
    override fun getMyViewModel() = viewModel
    override fun getLayoutId()= R.layout.activity_main




}
