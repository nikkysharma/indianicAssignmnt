package com.example.indianicassigment.view.base

import android.app.Dialog
import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import dagger.android.support.AndroidSupportInjection

/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
abstract class BaseDialogFragment<out T:ViewDataBinding,out V: BaseViewModel<out BaseNavigator>>
    : DialogFragment() {
    private var mActivity: BaseActivity<*, *>? = null
    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V

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
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // creating the dialog
        val dialog = context?.let { Dialog(it) }

        if (dialog != null) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCanceledOnTouchOutside(true)
        }



        return dialog!!
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BaseActivity<*, *>){
            mActivity = context
        }
        performDI()
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    fun getViewBinding() = mViewDataBinding
    fun getBaseActivity() = mActivity

    private fun performDI() {
        AndroidSupportInjection.inject(this)
    }

    fun showDialog(fragmentManager: FragmentManager, tag: String) {
        val transaction = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }
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
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int
}