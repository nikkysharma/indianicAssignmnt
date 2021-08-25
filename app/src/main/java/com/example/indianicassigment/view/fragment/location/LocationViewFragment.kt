package com.example.indianicassigment.view.fragment.location

import android.os.Bundle
import android.view.View
import com.example.indianicassigment.R
import com.example.indianicassigment.databinding.FragmentLocationBinding
import com.example.indianicassigment.view.base.BaseFragment
import com.example.indianicassigment.BR
import javax.inject.Inject

class LocationViewFragment : BaseFragment<FragmentLocationBinding, LocationViewFragmentViewModel>(),
    LocationFrgNavigator {



    @Inject
    protected lateinit var mViewModel: LocationViewFragmentViewModel
    private lateinit var binding: FragmentLocationBinding
    override fun getViewModel() = mViewModel
    override fun getBindingVariable() = BR.locationViewModel
    override fun getLayoutId() = R.layout.fragment_location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = getViewBinding()
        mViewModel.setNavigator(this)




    }



    override fun isError(errorTitle: String, errorMessage: String) {

    }

    override fun isError(error: String) {

    }

    override fun isSuccess(tag: Int,message :String) {


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

}
