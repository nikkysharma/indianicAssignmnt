package com.example.indianicassigment.utils


import android.content.Context
import android.net.ConnectivityManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout

import com.bumptech.glide.request.RequestOptions
import com.example.indianicassigment.BuildConfig
import okhttp3.OkHttpClient
import java.security.cert.X509Certificate
import javax.net.ssl.*
import javax.security.cert.CertificateException


/**
 * Created by Abhinav Sharma version 1 on 03/02/2019.
 */
object ConstantData {
    const val PACKAGE_NAME = BuildConfig.APPLICATION_ID








    const val DB_NAME = "Authentic.db"
    const val DB_VERSION = 1
    const val SHARED_PREF_NAME = "app-shared-preference"

    const val BASE_URL = "https://jsonplaceholder.typicode.com"



    const val UPDATE_INTERVAL: Long = 6000 // Every 6 seconds.

    /**
     * The fastest rate for active location updates. Updates will never be more frequent
     * than this value, but they may be less frequent.
     */
    const val FASTEST_UPDATE_INTERVAL: Long = 3000 // Every 3 seconds

    /**
     * The max time before batched results are delivered by location services. Results may be
     * delivered sooner than this interval.
     */
    const val MAX_WAIT_TIME = UPDATE_INTERVAL * 5 // Every 30 seconds.

}// This utility class is not publicly instantiable

object ApiUtils{


}// This utility class is not publicly instantiable

object CommonUtils {

    fun showLoadingDialog(context: Context,rootView: View):ProgressBar{
        val layout = rootView as ViewGroup
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT)

        val mProgressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLargeInverse)
        mProgressBar.setIndeterminate(true)

        val rl = RelativeLayout(context)

        rl.gravity = Gravity.CENTER
        rl.addView(mProgressBar)

        layout.addView(rl, params)

        return mProgressBar
    }
    fun hideLoadingDialog(rootView: ViewGroup?,parentView: View?){
        if(rootView == null || parentView == null)
            return
        rootView.removeView(parentView)
    }

}// This utility class is not publicly instantiable

object NetworkUtils {
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}// This class is not publicly instantiable

object LocationUtils{
    /**
     * Sets up the location request. Android has two location request settings:
     * `ACCESS_COARSE_LOCATION` and `ACCESS_FINE_LOCATION`. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     *
     *
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     *
     *
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */

}//This class is not publicly instantiable

object HttpClientUtils {

    fun getUnsafeOkHttpClient(): OkHttpClient {
        try {

            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                val allAcceptedIssuers: Array<java.security.cert.X509Certificate>
                    get() = arrayOf()

                override fun getAcceptedIssuers(): Array<X509Certificate> = allAcceptedIssuers

                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.getSocketFactory()

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String, session: SSLSession): Boolean {
                    return true
                }
            })

            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}// This class is not publicly instantiable

fun defaultRequest(placeHolder:Int, errorPlaceHolder:Int) =
        RequestOptions.placeholderOf(placeHolder).error(errorPlaceHolder)