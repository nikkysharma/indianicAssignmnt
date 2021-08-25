package com.example.indianicassigment.view.fragment.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.indianicassigment.BR
import com.example.indianicassigment.R
import com.example.indianicassigment.databinding.FragmentDetailBinding
import com.example.indianicassigment.view.base.BaseFragment
import javax.inject.Inject


class DetailFragment : BaseFragment<FragmentDetailBinding, DetailFragmentViewModel>() ,
    DetailFrgNavigator {





    @Inject
    protected lateinit var mViewModel: DetailFragmentViewModel

    private lateinit var binding: FragmentDetailBinding

    override fun getViewModel() = mViewModel
    override fun getBindingVariable() = BR.detailFrgViewModel
    override fun getLayoutId() = R.layout.fragment_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SimpleDateFormat", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.setNavigator(this)

        binding = getViewBinding()
           setData()




    }



     fun setData() {
        val strtext = requireArguments().getString("id")?.toInt()
        val userData = strtext?.let { mViewModel.getMaxtra(it) }
        if(userData!=null) {

            binding.txtName.text= ""+userData.name
            binding.txtLocation.text= ""+userData.locationAddress
            binding.txtNeed.text= ""+userData.helpNeededFor

        }
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
        return true
    }

    override fun isBack(): Boolean {

        return true
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
