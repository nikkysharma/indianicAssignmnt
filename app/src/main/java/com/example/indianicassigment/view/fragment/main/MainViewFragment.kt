package com.example.indianicassigment.view.fragment.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.indianicassigment.R
import com.example.indianicassigment.databinding.FragmentMainViewBinding
import com.example.indianicassigment.view.base.BaseFragment
import com.example.indianicassigment.BR
import com.example.indianicassigment.data.model.api.MaxtraResponse
import com.example.indianicassigment.view.fragment.wallet_fragment.HaskListAdapterTwo
import com.example.projectsetwithmvvmdagger.view.navigator.HashListNavigator

import javax.inject.Inject




class MainViewFragment : BaseFragment<FragmentMainViewBinding, MainViewFragmentViewModel>(),
    MainViewFrgNavigator, HashListNavigator {



    @Inject
    protected lateinit var mViewModel: MainViewFragmentViewModel

    private lateinit var binding:FragmentMainViewBinding

    override fun getViewModel() = mViewModel
    override fun getBindingVariable() = BR.mainFrgViewModel
    override fun getLayoutId() = R.layout.fragment_main_view
    @Inject
    protected lateinit var adapter: HaskListAdapterTwo
    var arrayListHashList: ArrayList<MaxtraResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideKeyboard()
        if (arguments != null) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.setNavigator(this)
        binding = getViewBinding()
        mViewModel.listInCall(activity).observe(viewLifecycleOwner, changeObserver)

    }

    private val changeObserver = Observer<List<MaxtraResponse>> { value ->
        value?.let {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.setVisibility(View.GONE);
            adapter.removeAll()
            adapter.addAll(it)
            binding.shimmerRecyclerView.setLayoutManager(GridLayoutManager(activity, 1) as RecyclerView.LayoutManager?)
            binding.shimmerRecyclerView.hasFixedSize()
            binding.shimmerRecyclerView.adapter = adapter

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
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun isHeader(): Boolean {
        return true
    }

    override fun isBack(): Boolean {

        return false
    }

    override fun isDrawer(): Boolean {
        return true
    }

    override fun getTitle(): String {
        return getString(R.string.app_name)
    }
    override fun onDrawerClick(): View.OnClickListener {
        return View.OnClickListener {  }
    }



    override fun clickedItem(id: String) {
        val bundel = Bundle()
        bundel.putString("id",id)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundel)
    }

}
