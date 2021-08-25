package com.example.indianicassigment.view.fragment.login


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.indianicassigment.BR
import com.example.indianicassigment.R
import com.example.indianicassigment.databinding.FragmentLoginBinding
import com.example.indianicassigment.utils.DialogFactory
import com.example.indianicassigment.utils.Util
import com.example.indianicassigment.view.base.BaseFragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginFragmentViewModel>(),
    LogInFrgNavigator{



    @Inject
    protected lateinit var mViewModel: LoginFragmentViewModel

    private lateinit var binding:FragmentLoginBinding
    override fun getViewModel() = mViewModel
    override fun getBindingVariable() = BR.loginFrgViewModel
    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    override fun getLayoutId() = R.layout.fragment_login
    private var storedVerificationId: String? = ""
    private var phoneNum: String = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
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
        auth=FirebaseAuth.getInstance()
        binding.loginBtnLog.setOnClickListener {
            openOtp()
        }


    }




    override fun openOtp() {
       // findNavController().navigate(R.id.action_loginFragment_to_OtpFragment)
        hideKeyboard()
        val mobile = binding.etMobile.getText().toString().trim()
        if (TextUtils.isEmpty(mobile)) {
            DialogFactory.fancyAlert(activity, getString(R.string.enter_mobile))
            return
        }
        if (mobile.length<10) {
            DialogFactory.fancyAlert(activity, getString(R.string.enter_valid_mobile))
            return
        }
        sendVerificationCode("+91"+mobile)

    }
    private fun sendVerificationCode(number: String) {
        // this method is used for getting
        // OTP on user phone number.
        Util.showProgress(requireActivity())
        phoneNum=number
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(mCallBack) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    // callback method is called on Phone auth provider.
     // initializing our callbacks for on
    // verification callback method.
    private val mCallBack: OnVerificationStateChangedCallbacks =
        object : OnVerificationStateChangedCallbacks() {
            // below method is used when
            // OTP is sent from Firebase
            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                // when we receive the OTP it
                // contains a unique id which
                // we are storing in our string
                // which we have already created.
                storedVerificationId = s
                resendToken = forceResendingToken
                Util.hideProgress()
//                // checking if the code
//                // is null or not.
////                if (code != null) {
////                    // if the code is not null then
                    // we are setting that code to
                        // after setting this code
                        verifyCode("")

            }

            // this method is called when user
            // receive OTP from Firebase.
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                // below line is used for getting OTP code
                // which is sent in phone auth credentials.
                val code = phoneAuthCredential.smsCode
                Util.hideProgress()
                // checking if the code
                // is null or not.
//                if (code != null) {
//                    // if the code is not null then
//                    // we are setting that code to
//                        // after setting this code
//                        verifyCode(code)
//                }
            }

            // this method is called when firebase doesn't
            // sends our OTP code due to any error or issue.
            override fun onVerificationFailed(e: FirebaseException) {
                // displaying error message with firebase exception.
                DialogFactory.fancyAlert(activity, e.message)
                Util.hideProgress()
                Toast.makeText(requireActivity(), e.message, Toast.LENGTH_LONG).show()
            }
        }

    // below method is use to verify code from Firebase.
    private fun verifyCode(code: String) {
        val bundle = Bundle()
        bundle.putString("code",code)
        bundle.putString("id",storedVerificationId)
        bundle.putString("phone",phoneNum)
        findNavController().navigate(R.id.action_loginFragment_to_OtpFragment,bundle)
    }
    override fun isError(errorTitle: String, errorMessage: String) {
        Util.hideProgress()

    }

    override fun isError(error: String) {
        Util.hideProgress()

    }

    override fun isSuccess(tag: Int,message :String) {
        Util.hideProgress()
       // AppToast.show(activity,message, true)
    }


    override fun getHeaderView(): View {
        return view?.findViewById(R.id.header) as View
        //To change body of created functions use File | Settings | File Templates.
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
