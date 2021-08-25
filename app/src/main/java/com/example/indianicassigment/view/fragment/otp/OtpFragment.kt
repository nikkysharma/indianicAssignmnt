package com.example.indianicassigment.view.fragment.otp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.indianicassigment.R
import com.example.indianicassigment.databinding.FragmentOtpBinding
import com.example.indianicassigment.view.base.BaseFragment
import com.example.indianicassigment.BR
import com.example.indianicassigment.utils.DBConstants
import com.example.indianicassigment.utils.DialogFactory
import com.example.indianicassigment.utils.Util
import com.example.indianicassigment.view.activity.main.MainActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OtpFragment : BaseFragment<FragmentOtpBinding, OtpViewModel>(),
    OtpNavigation {



    @Inject
    protected lateinit var mViewModel: OtpViewModel
    private lateinit var binding: FragmentOtpBinding
    override fun getViewModel() = mViewModel
    override fun getBindingVariable() = BR.otpFrgViewModel
    override fun getLayoutId() = R.layout.fragment_otp
    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String? = ""
    private var code: String? = ""
    private var phoneNum: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var COUNTDOWN_INTERVAL = 1000
    var mCountDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            code= requireArguments().getString("code")
            storedVerificationId= requireArguments().getString("id")
            phoneNum= requireArguments().getString("phone")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth=FirebaseAuth.getInstance()
        binding = getViewBinding()
        mViewModel.setNavigator(this)
        conterStart()
        binding.txtPinEntry.setText(code)
        binding.btnSubmit.setOnClickListener {
            val otp :String = binding.txtPinEntry.text.toString()
            if (otp != null) {
                if (otp.length < 6) {
                    DialogFactory.fancyAlert(activity, getString(R.string.enter_valid_otp))
                    binding.txtPinEntry.requestFocus()
                }
                else {
                    val credential = storedVerificationId?.let { PhoneAuthProvider.getCredential(it, otp) }
                    if (credential != null) {
                        signInWithPhoneAuthCredential(credential)
                    }

                }
            }
        }

//
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
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")

                    mViewModel.mDataManager.setBooleanData(DBConstants.IS_LOGGED_IN, true)
                    openMainActivity()
                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        DialogFactory.fancyAlert(activity, getString(R.string.enter_valid_otp))
                    }
                    // Update UI
                }
            }
    }

    private fun openMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
    // [START resend_verification]
    private fun resendVerificationCode(
        phoneNumber: String
    ) {
                // OnVerificationStateChangedCallbacks
        Util.showProgress(requireActivity())
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(mCallBack) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    // callback method is called on Phone auth provider.
    // initializing our callbacks for on
    // verification callback method.
    private val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            // below method is used when
            // OTP is sent from Firebase
            override fun onCodeSent(s: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                // when we receive the OTP it
                // contains a unique id which
                // we are storing in our string
                // which we have already created.
                storedVerificationId = s
                resendToken = forceResendingToken
            }

            // this method is called when user
            // receive OTP from Firebase.
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                // below line is used for getting OTP code
                // which is sent in phone auth credentials.
                val code = phoneAuthCredential.smsCode

                // checking if the code
                // is null or not.
                if (code != null) {
                    // if the code is not null then
                    // we are setting that code to
                    // our OTP edittext field.
                     Util.hideProgress()
                    conterStart()
                    binding.txtPinEntry.setText(code)
                    // after setting this code
                    // to OTP edittext field we
                    // are calling our verifycode method.

                    verifyCode(code)
                }
            }

            // this method is called when firebase doesn't
            // sends our OTP code due to any error or issue.
            override fun onVerificationFailed(e: FirebaseException) {
                // displaying error message with firebase exception.
                DialogFactory.fancyAlert(activity, e.message)
                Toast.makeText(requireActivity(), e.message, Toast.LENGTH_LONG).show()
            }
        }

    // below method is use to verify code from Firebase.
    private fun verifyCode(code: String) {
        val credential = storedVerificationId?.let { PhoneAuthProvider.getCredential(it, code) }
        if (credential != null) {
            signInWithPhoneAuthCredential(credential)
        }
    }
    private fun conterStart() {
        binding.textResendOTp.isEnabled = false
        mCountDownTimer = object : CountDownTimer(120000, COUNTDOWN_INTERVAL.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countDownTimer.visibility = View.VISIBLE
                val second: Long = millisUntilFinished / 1000 % 60
                val minute: Long = millisUntilFinished / (1000 * 60) % 60
                if (second>9){
                    binding.countDownTimer.text = "0"+minute+":"+second
                }else{
                    binding.countDownTimer.text = "0"+minute+":0"+second
                }

            }

            override fun onFinish() {
                binding.countDownTimer.text = "00:00"
                binding.textResendOTp.isEnabled = true
                binding.textResendOTp.setOnClickListener {
                    phoneNum?.let { it1 -> resendVerificationCode(it1) }

                }
            }
        }
        mCountDownTimer!!.start()

    }
}
