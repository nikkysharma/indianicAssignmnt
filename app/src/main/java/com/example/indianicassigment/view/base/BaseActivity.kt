package com.example.indianicassigment.view.base


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatDelegate
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.indianicassigment.R
import com.example.indianicassigment.utils.CommonUtils
import com.example.indianicassigment.view.activity.main.MainActivity


import dagger.android.support.DaggerAppCompatActivity


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
abstract class BaseActivity<out T: ViewDataBinding, out V: BaseViewModel<out BaseNavigator>>: DaggerAppCompatActivity() {
    private lateinit var mViewDataBinding: T
    private var mViewModel: V? =  null
    private var mProgress:ProgressBar? = null
    private val REQUESTCHECKSETTINGS = 0x00000001
    private var fragmentLocation: Fragment? = null

    private var lastClickedTime: Long = 0
    private var navController: NavController? = null
    companion object {
        init {

            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()

        if (this is MainActivity) {
            navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        }


    }




    fun handleHeader(view: View, isBack: Boolean, isDrawer: Boolean, title: String, onClickListener: View.OnClickListener) {
        val imgBack = view.findViewById<ImageView>(R.id.img_back)
        val imgRight = view.findViewById<ImageView>(R.id.img_right)
        val imgHumbour = view.findViewById<ImageView>(R.id.img_humbur)
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)

        tvTitle.text = title
        if (isBack) {
            imgBack.visibility = View.VISIBLE
            imgBack.setOnClickListener {
                onBackPressed() }
        } else {
            imgBack.visibility = View.GONE
        }
        if (isDrawer) {
            imgHumbour.visibility = View.VISIBLE
            imgHumbour.setOnClickListener(onClickListener)
        } else {
            imgHumbour.visibility = View.GONE
            imgHumbour.setOnClickListener { }
        }
    }





    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil
                .setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getMyViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    fun hideKeyboard(){
        val view = this.currentFocus
        if(null != view){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken,0)
        }
    }



    fun showLoading(){
        hideLoading()
        mProgress = CommonUtils.showLoadingDialog(this,mViewDataBinding.root)
    }

    fun hideLoading(){
        if(null != mProgress){
            CommonUtils.hideLoadingDialog(mViewDataBinding.root as ViewGroup,mProgress!!.parent as ViewGroup)
            mProgress = null
        }
    }

    abstract fun getBindingVariable(): Int
    abstract fun getMyViewModel(): V
    @LayoutRes
    abstract fun getLayoutId():Int

    protected fun getViewDataBinding():T = mViewDataBinding

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUESTCHECKSETTINGS -> {

            }
            else -> {
                //TODO handle default implementation
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
    fun addFragment(fragment: Fragment, addToBackStack: Boolean, resourceId: Int, anim: Boolean) {
        val transaction = this.supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        if (anim)
        transaction.setCustomAnimations(R.anim.right_to_left_enter, R.anim.right_to_left_exit);
        transaction.add(resourceId, fragment)
        transaction.commitAllowingStateLoss()

    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean, resourceId: Int) {
        val transaction = this.supportFragmentManager.beginTransaction()



        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.replace(resourceId, fragment)
        transaction.commitAllowingStateLoss()
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    fun getNavController(): NavController? {
        return navController
    }
}