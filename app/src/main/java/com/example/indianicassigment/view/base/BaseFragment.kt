package com.example.indianicassigment.view.base

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
abstract class BaseFragment<out T:ViewDataBinding,out V: BaseViewModel<out BaseNavigator>>
    : DaggerFragment() {
    private var mActivity: BaseActivity<*, *>? = null
    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V
 //   private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        val mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModel()
        mViewDataBinding.setVariable(getBindingVariable(),mViewModel)
        mViewDataBinding.executePendingBindings()

//        try {
//            navController = Navigation.findNavController(view)
//        } catch (e: Exception) {
//            Log.d("BaseFragment", "view does not have a NavController set")
//        }

    }
    override fun onResume() {
        super.onResume()
        if (isHeader()) {
            if (getHeaderView() != null) {
                getHeaderView()!!.setVisibility(View.VISIBLE)
                getBaseActivity()!!.handleHeader(getHeaderView()!!, isBack(), isDrawer(), getTitle(), onDrawerClick())
            }
            else {
                if (getHeaderView() != null)
                    getHeaderView()!!.setVisibility(View.GONE)
            }
        }
        else{
            getHeaderView()!!.setVisibility(View.GONE)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BaseActivity<*, *>){
            mActivity = context
        }
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }



    fun hideKeyboard() {
        if (mActivity != null) {
            mActivity!!.hideKeyboard()
        }
    }
    fun showLoading(){
        if (mActivity != null)
            mActivity!!.showLoading()

    }

    fun hideLoading(){
        if (mActivity != null) {
            mActivity!!.hideLoading()
        }
    }

    fun getBaseActivity() = mActivity
    fun getViewBinding() = mViewDataBinding

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract fun getBindingVariable(): Int


    /**
     * Gets header view.
     *
     * @return the header view
     */
    protected abstract fun getHeaderView(): View?

    /**
     * Is header boolean.
     *
     * @return the boolean
     */
    protected abstract fun isHeader(): Boolean

    /**
     * Is back boolean.
     *
     * @return the boolean
     */
    protected abstract fun isBack(): Boolean

    /**
     * Is right boolean.
     *
     * @return the boolean
     */
    protected abstract fun isDrawer(): Boolean

    /**
     * Gets title.
     *
     * @return the title
     */
    protected abstract fun getTitle(): String

    protected abstract fun onDrawerClick(): View.OnClickListener
    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    fun addFragment(fragment: Fragment, addToBackStack: Boolean, resourceId: Int, anim: Boolean) {
       getBaseActivity()!!.addFragment(fragment,addToBackStack,resourceId,anim);
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean, resourceId: Int) {
        getBaseActivity()!!.replaceFragment(fragment,addToBackStack,resourceId);
    }

//    fun getNavController(): NavController? {
//        return this.navController
//    }
}