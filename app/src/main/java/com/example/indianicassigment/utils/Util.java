package com.example.indianicassigment.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


import com.example.indianicassigment.R;

import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import static android.content.Context.WIFI_SERVICE;

public class Util {
    private static boolean isLoadingVisible;
    private static Dialog mProgressDialog;
    private static AlertDialog dialog;
    private static AnimationDrawable animationDrawable;
    private static ImageView mProgressBar;
    /**
     * Show progress.
     *
     * @param mContext the m context
     */

    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected();
    }
   public static String getTimeDiffreneLastSeen(String time){
       String dateStart = time;
       String timeRetur="";

       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       Date date = new Date();
       System.out.println(dateFormat.format(date));
       String dateStop = dateFormat.format(date);
       //HH converts hour in 24 hours format (0-23), day calculation
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

       Date d1 = null;
       Date d2 = null;

       try {
           d1 = format.parse(dateStart);
           d2 = format.parse(dateStop);

           //in milliseconds
           long diff = d2.getTime() - d1.getTime();

           long diffSeconds = diff / 1000 % 60;
           long diffMinutes = diff / (60 * 1000) % 60;
           long diffHours = diff / (60 * 60 * 1000) % 24;
           long diffDays = diff / (24 * 60 * 60 * 1000);
           long diffmounth = diffDays / 30;

           System.out.print(diffDays + " days, ");
           System.out.print(diffHours + " hours, ");
           System.out.print(diffMinutes + " minutes, ");
           System.out.print(diffSeconds + " seconds.");


           if (diffmounth>0){
               timeRetur=""+diffmounth+" mounth";
               return timeRetur;
           }
          else{
              if (diffDays>0){
                  timeRetur=""+diffDays+" days";
                   return timeRetur;
               }
              else {
                  if (diffHours>0){
                      timeRetur=""+diffHours+" hours";
                      return timeRetur;

                  }
                  else {
                      if (diffMinutes>0){
                          timeRetur=""+diffMinutes+" seconds";
                          return timeRetur;
                      }
                      else {
                          timeRetur=""+diffSeconds;
                          return timeRetur;
                      }

                  }

              }
           }

       } catch (Exception e) {
           e.printStackTrace();
       }
     return timeRetur;
   }

    public static String getTimeAge(String time){
        String dateStart = time;
        String timeRetur="";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String dateStop = dateFormat.format(date);
        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);
            long diffmounth = diffDays / 30;

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");


            if (diffmounth>12){
                long year = diffmounth/12;
               // timeRetur=""+diffmounth+" mounth";
                return ""+year;
            }
            else{
                return ""+0;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeRetur;
    }



    public static void showProgress(Context mContext) {
        if (mProgressDialog == null) {
            mProgressDialog = new Dialog(mContext);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setContentView(R.layout.progress_dialog);
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
            mProgressBar = (ImageView)mProgressDialog.findViewById(R.id.main_progress);
            mProgressBar.setBackgroundResource(R.drawable.pica_animator);
            animationDrawable = (AnimationDrawable)mProgressBar.getBackground();

            if (!mProgressDialog.isShowing()){
                mProgressBar.setVisibility(View.VISIBLE);
                animationDrawable.start();
                mProgressDialog.show();
            }
        }
    }

    /**
     * Hide progress.
     */
    public static void hideProgress() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
                isLoadingVisible = false;
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        mProgressBar.setVisibility(View.GONE);
        animationDrawable.stop();
    }

    /**
     * Show alert box.
     *
     * @param context          the context
     * @param title            the title
     * @param msg              the msg
     * @param btn1             the btn 1
     * @param btn2             the btn 2
     * @param clickListenerYes the click listener yes
     * @param clickListenerNo  the click listener no
     * @param isClose          the is close
     * @return the alert dialog
     */


    /**
     * Email validator boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean emailValidator(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm:ss");

        String localTime = date.format(currentLocalTime);
        return localTime;
    }

    public static long getTimeDiffrence(String startTime, String endTime) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date1 = simpleDateFormat.parse(startTime);
            Date date2 = simpleDateFormat.parse(endTime);

            long difference = date2.getTime() - date1.getTime();
            return difference;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Password validator boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean passwordValidator(String password) {
        /*Pattern pattern;
        Matcher matcher;
        final String PASS_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
        pattern = Pattern.compile(PASS_PATTERN);
        matcher = pattern.matcher(password);*/
        boolean isRight = true;
        if (password != null && password.trim().length() < 6)
            isRight = false;
        return isRight;
    }

    /**
     * Request all runtime permissions.
     *
     * @param activity the activity
     */
    public static void requestAllRuntimePermissions(Activity activity) {
        String[] permissions = getAllPermissions(activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions != null && permissions.length > 0) {
            activity.requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);
        }
    }




    private static String[] getAllPermissions(final Context context) {
//        List<String> notGranted = new ArrayList<String>();
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            /*for (int i = 0; i < pi.requestedPermissions.length; i++) {
                if ((pi.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0) {
                    notGranted.add(pi.requestedPermissions[i]);
                }
            }*/
            return pi.requestedPermissions;
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * Request read write permission.
     *
     * @param activity the activity
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void RequestReadWritePermission(Activity activity) {
        activity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
    }

    /**
     * Request read write and camera permission.
     *
     * @param activity the activity
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void RequestReadWriteAndCameraPermission(Activity activity) {
        activity.requestPermissions(new String[]{Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_READ_WRITE);
    }


    /**
     * Request location enabke.
     *
     * @param activity the activity
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestLocationEnabke(Activity activity) {
        activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
        }, REQUEST_LOCATION);
    }

    /**
     * Gets image bytesfor server pod.
     *
     * @return the image bytesfor server pod
     */
    public static ArrayList<String> getImageBytesforServer_pod() {
        return ImageBytesforServer_pod;
    }

    /**
     * Sets image bytesfor server pod.
     *
     * @param imageBytesforServer_pod the image bytesfor server pod
     */
    public static void setImageBytesforServer_pod(ArrayList<String> imageBytesforServer_pod) {
        ImageBytesforServer_pod = imageBytesforServer_pod;
    }

    /**
     * The constant ImageBytesforServer_pod.
     */
    public static ArrayList<String> ImageBytesforServer_pod = new ArrayList<String>();


    /**
     * The constant EMAIL_PATTERN.
     */
    public static String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final boolean IS_DEBUG = true;

    /**
     * Show key borad.
     *
     * @param context the context
     * @param view    the view
     */
    public static void showKeyBorad(Context context, View view) {
        InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * Hide key borad.
     *
     * @param context the context
     * @param view    the view
     */
    public static void hideKeyBorad(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Hide key borad.
     *
     * @param context the context
     */
    public static void hideKeyBorad(Context context) {
        if (context instanceof Activity) {
            View view = ((Activity) context).getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


    /**
     * Is online boolean.
     *
     * @return the boolean
     */


    /**
     * Show log.
     *
     * @param logMessage the log message
     */
    public static void showLog(String logMessage) {
        if (IS_DEBUG) {
            Log.d("GRABGEINE LOG :", logMessage);
        }
    }


    /**
     * The constant REQUEST_CAMERA.
     */
    public static final int REQUEST_CAMERA = 123;
    /**
     * The constant REQUEST_CODE_ASK_PERMISSIONS.
     */
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 12345;
    /**
     * The constant REQUEST_CAMERA_READ_WRITE.
     */
    public static final int REQUEST_CAMERA_READ_WRITE = 1234;
    /**
     * The constant REQUEST_LOCATION.
     */
    public static final int REQUEST_LOCATION = 1234;


    /**
     * Is mobile data available boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isMobileDataAvailable(Context context) {
        boolean mobileDataEnabled = false; // Assume disabled
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true); // Make the method callable
            // get the setting for "mobile data"
            mobileDataEnabled = (Boolean) method.invoke(cm);
            return true;

        } catch (Exception e) {

        }
        return false;
    }

    /**
     * Get address from lat long string.
     *
     * @param context   the context
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the string
     */
    public static String getAddressFromLatLong(Context context, Double latitude, Double longitude) {
        try {

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            return address;
        } catch (Exception e) {

        }
        return "";
    }

    public static List<Address> getAddressFromLatLong(Context context, Double latitude, Double longitude, boolean isAddress) {
        try {

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            return addresses;
        } catch (Exception e) {

        }
        return null;
    }


    /**
     * Is empty boolean.
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isEmpty(String str) {
        if (str != null && str.trim().length() > 0)
            return false;
        else
            return true;
    }

    /**
     * Gets circular bitmap.
     *
     * @param srcBitmap the src bitmap
     * @return the circular bitmap
     */
    public static Bitmap getCircularBitmap(Bitmap srcBitmap) {
        // Calculate the circular bitmap width with border
        int squareBitmapWidth = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());

        Bitmap newBitmap = Bitmap.createBitmap(srcBitmap);

        // Initialize a new instance of Bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(
                squareBitmapWidth, // Width
                squareBitmapWidth, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        /*
            Canvas
                The Canvas class holds the "draw" calls. To draw something, you need 4 basic
                components: A Bitmap to hold the pixels, a Canvas to host the draw calls (writing
                into the bitmap), a drawing primitive (e.g. Rect, Path, text, Bitmap), and a paint
                (to describe the colors and styles for the drawing).
        */
        // Initialize a new Canvas to draw circular bitmap
        Canvas canvas = new Canvas(dstBitmap);

        // Initialize a new Paint instance
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        /*
            Rect
                Rect holds four integer coordinates for a rectangle. The rectangle is represented by
                the coordinates of its 4 edges (left, top, right bottom). These fields can be accessed
                directly. Use width() and height() to retrieve the rectangle's width and height.
                Note: most methods do not check to see that the coordinates are sorted correctly
                (i.e. left <= right and top <= bottom).
        */
        /*
            Rect(int left, int top, int right, int bottom)
                Create a new rectangle with the specified coordinates.
        */
        // Initialize a new Rect instance
        Rect rect = new Rect(0, 0, squareBitmapWidth, squareBitmapWidth);

        /*
            RectF
                RectF holds four float coordinates for a rectangle. The rectangle is represented by
                the coordinates of its 4 edges (left, top, right bottom). These fields can be
                accessed directly. Use width() and height() to retrieve the rectangle's width and
                height. Note: most methods do not check to see that the coordinates are sorted
                correctly (i.e. left <= right and top <= bottom).
        */
        // Initialize a new RectF instance
        RectF rectF = new RectF(rect);

        /*
            public void drawOval (RectF oval, Paint paint)
                Draw the specified oval using the specified paint. The oval will be filled or
                framed based on the Style in the paint.

            Parameters
                oval : The rectangle bounds of the oval to be drawn

        */
        // Draw an oval shape on Canvas
        canvas.drawOval(rectF, paint);

        /*
            public Xfermode setXfermode (Xfermode xfermode)
                Set or clear the xfermode object.
                Pass null to clear any previous xfermode. As a convenience, the parameter passed
                is also returned.

            Parameters
                xfermode : May be null. The xfermode to be installed in the paint
            Returns
                xfermode
        */
        /*
            public PorterDuffXfermode (PorterDuff.Mode mode)
                Create an xfermode that uses the specified porter-duff mode.

            Parameters
                mode : The porter-duff mode that is applied

        */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // Calculate the left and top of copied bitmap
        float left = (squareBitmapWidth - srcBitmap.getWidth()) / 2;
        float top = (squareBitmapWidth - srcBitmap.getHeight()) / 2;

        /*
            public void drawBitmap (Bitmap bitmap, float left, float top, Paint paint)
                Draw the specified bitmap, with its top/left corner at (x,y), using the specified
                paint, transformed by the current matrix.

                Note: if the paint contains a maskfilter that generates a mask which extends beyond
                the bitmap's original width/height (e.g. BlurMaskFilter), then the bitmap will be
                drawn as if it were in a Shader with CLAMP mode. Thus the color outside of the

                original width/height will be the edge color replicated.

                If the bitmap and canvas have different densities, this function will take care of
                automatically scaling the bitmap to draw at the same density as the canvas.

            Parameters
                bitmap : The bitmap to be drawn
                left : The position of the left side of the bitmap being drawn
                top : The position of the top side of the bitmap being drawn
                paint : The paint used to draw the bitmap (may be null)
        */
        // Make a rounded image by copying at the exact center position of source image
        canvas.drawBitmap(newBitmap, left, top, paint);

        // Free the native object associated with this bitmap.
//        srcBitmap.recycle();

        // Return the circular bitmap
        return dstBitmap;
    }

    /**
     * Add border to circular bitmap bitmap.
     *
     * @param srcBitmap   the src bitmap
     * @param borderWidth the border width
     * @param borderColor the border color
     * @return the bitmap
     */
// Custom method to add a border around circular bitmap
    public static Bitmap addBorderToCircularBitmap(Bitmap srcBitmap, int borderWidth, int borderColor) {
        // Calculate the circular bitmap width with border
        int dstBitmapWidth = srcBitmap.getWidth() + borderWidth * 2;

        // Initialize a new Bitmap to make it bordered circular bitmap
        Bitmap dstBitmap = Bitmap.createBitmap(dstBitmapWidth, dstBitmapWidth, Bitmap.Config.ARGB_8888);

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(dstBitmap);
        // Draw source bitmap to canvas
        canvas.drawBitmap(srcBitmap, borderWidth, borderWidth, null);

        // Initialize a new Paint instance to draw border
        Paint paint = new Paint();
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        paint.setAntiAlias(true);

        /*
            public void drawCircle (float cx, float cy, float radius, Paint paint)
                Draw the specified circle using the specified paint. If radius is <= 0, then nothing
                will be drawn. The circle will be filled or framed based on the Style in the paint.

            Parameters
                cx : The x-coordinate of the center of the cirle to be drawn
                cy : The y-coordinate of the center of the cirle to be drawn
                radius : The radius of the cirle to be drawn
                paint : The paint used to draw the circle
        */
        // Draw the circular border around circular bitmap
        canvas.drawCircle(
                canvas.getWidth() / 2, // cx
                canvas.getWidth() / 2, // cy
                canvas.getWidth() / 2 - borderWidth / 2, // Radius
                paint // Paint
        );

        // Free the native object associated with this bitmap.
        srcBitmap.recycle();

        // Return the bordered circular bitmap
        return dstBitmap;
    }

    /**
     * Date picker.
     *
     * @param context   the context
     * @param tv        the tv
     * @param minDate   the min date
     * @param isMinDate the is min date
     * @param isDOB     the is dob
     */
//    public static void datePicker(Context context, final View tv, String minDate, boolean isMinDate,boolean isDOB) {
//
//
//        final Calendar myCalendar = Calendar.getInstance();
//
//        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                String myFormat = "dd MMM yyyy"; //In which you need put here
//                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//                if (tv instanceof TextView) {
//                    TextView textView = (TextView) tv;
//                    textView.setText(sdf.format(myCalendar.getTime()));
//                    textView.setTextColor(Color.BLACK);
//                }
//
//            }
//
//        };
//        Date d = null;
//        try {
//
//            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//
//            if (minDate != null) {
//                d = sdf.parse(minDate);
//            } else {
//                Calendar cal = Calendar.getInstance();
//                if(isDOB){
////                        cal.add(Calendar.DAY_OF_MONTH, 0);
//                }else
//                    cal.add(Calendar.DAY_OF_MONTH, 1);
//
//
//                   /* String strDate = "2000-05-15T10:00:00-0700";
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//                    Date dd = dateFormat.parse(strDate);
//                    System.out.println(date);*/
//                d = cal.getTime();
//            }
//
//        } catch (Exception e) {
//            e.fillInStackTrace();
//        }
//        DatePickerDialog datePicker = new DatePickerDialog(context, date, myCalendar
//                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                myCalendar.get(Calendar.DAY_OF_MONTH));
//
//        if(isDOB){
//            datePicker.getDatePicker().setMaxDate(new Date().getTime());
//        }else {
//            if(isMinDate)
//                datePicker.getDatePicker().setMinDate(d.getTime());
//        }
//        datePicker.show();
//    }

    /**
     * Change date format string.
     *
     * @param from        the form
     * @param forExpected the for expected
     * @param date        the date
     * @return the string
     */
    public static String changeDateFormat(String from, String forExpected, String date) {
        SimpleDateFormat format = new SimpleDateFormat(from);
        SimpleDateFormat formatExpected = new SimpleDateFormat(forExpected);
        try {

            Date start = format.parse(date);
            date = formatExpected.format(start);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return date;
    }

    /**
     * Force logout.
     *
     * @param context the context
     */
    static AlertDialog alertDialog1;


    /**
     * Is today or after today boolean.
     *
     * @param currentDate the current date
     * @param myDate      the my date
     * @return the boolean
     */
    public static boolean isTodayOrAfterToday(String currentDate, String myDate) {

        SimpleDateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");

        boolean isTodayOrAfterToday = false;

        try {
            if (dfDate.parse(currentDate).before(dfDate.parse(myDate))) {
                isTodayOrAfterToday = true; // If currentDate is before myDate.
            }
            if (dfDate.parse(currentDate).equals(dfDate.parse(myDate))) {
                isTodayOrAfterToday = true; // If two dates are equal.
            }
            if (dfDate.parse(currentDate).after(dfDate.parse(myDate))) {
                isTodayOrAfterToday = false; // If currentDate is after the myDate.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isTodayOrAfterToday;
    }

    /**
     * Is today or after today boolean.
     *
     * @param currentDate the current date
     * @param myDate      the my date
     * @param formate     the formate
     * @return the boolean
     */
    public static boolean isTodayOrAfterToday(String currentDate, String myDate, String formate) {

        SimpleDateFormat dfDate = new SimpleDateFormat(formate);

        boolean isTodayOrAfterToday = false;

        try {
            if (dfDate.parse(currentDate).before(dfDate.parse(myDate))) {
                isTodayOrAfterToday = true; // If currentDate is before myDate.
            }
            if (dfDate.parse(currentDate).equals(dfDate.parse(myDate))) {
                isTodayOrAfterToday = true; // If two dates are equal.
            }
            if (dfDate.parse(currentDate).after(dfDate.parse(myDate))) {
                isTodayOrAfterToday = false; // If currentDate is after the myDate.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isTodayOrAfterToday;
    }

    /**
     * Is today or after today with formate boolean.
     *
     * @param myDate  the my date
     * @param formate the formate
     * @return the boolean
     */
    public static boolean isTodayOrAfterTodayWithFormate(String myDate, String formate) {

        String currentDate = new SimpleDateFormat(formate, Locale.getDefault()).format(new Date());

        SimpleDateFormat dfDate = new SimpleDateFormat(formate);

        boolean isTodayOrAfterToday = false;

        try {
            if (dfDate.parse(currentDate).before(dfDate.parse(myDate))) {
                isTodayOrAfterToday = true; // If currentDate is before myDate.
            }
            if (dfDate.parse(currentDate).equals(dfDate.parse(myDate))) {
                isTodayOrAfterToday = true; // If two dates are equal.
            }
            if (dfDate.parse(currentDate).after(dfDate.parse(myDate))) {
                isTodayOrAfterToday = false; // If currentDate is after the myDate.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isTodayOrAfterToday;
    }

    /**
     * Get file size long.
     *
     * @param filePath the file path
     * @return the long
     */
    public static long getFileSize(String filePath) {
        File file = new File(filePath);
// Get length of file in bytes
        long fileSizeInBytes = file.length();
// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        long fileSizeInKB = fileSizeInBytes / 1024;
// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        long fileSizeInMB = fileSizeInKB / 1024;

        return fileSizeInMB;
    }


    public static void showTwoButtonsAlertBox(Context context, String msg, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        try {
            new AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.app_name)).setMessage(msg).setNegativeButton("NO", cancelListener).setPositiveButton("YES", okListener).show().setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void showAlertBox(Context context, String msg, DialogInterface.OnClickListener okListener) {
        try {
            new AlertDialog.Builder(context).setTitle(context.getResources().getString(R.string.app_name)).setMessage(msg).setPositiveButton("OK", okListener).show().setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    static String IPaddress;
    static Boolean IPValue;

    //Check the internet connection.
    public static String NetwordDetect(Activity activity) {

        boolean WIFI = false;

        boolean MOBILE = false;

        ConnectivityManager CM = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfo = CM.getAllNetworkInfo();

        for (NetworkInfo netInfo : networkInfo) {

            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))

                if (netInfo.isConnected())

                    WIFI = true;

            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))

                if (netInfo.isConnected())

                    MOBILE = true;
        }

        if (WIFI == true) {
            IPaddress = GetDeviceipWiFiData(activity);
            return IPaddress;


        }

        if (MOBILE == true) {

            IPaddress = GetDeviceipMobileData();
            return IPaddress;


        }
        return IPaddress;

    }


    public static String GetDeviceipMobileData() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface networkinterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = networkinterface.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("Current IP", ex.toString());
        }
        return null;
    }

    public static String GetDeviceipWiFiData(Activity activity) {

        WifiManager wm = (WifiManager) activity.getApplicationContext().getSystemService(WIFI_SERVICE);

        @SuppressWarnings("deprecation")

        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        return ip;

    }

    public static String datePicker(Context context, final View tv, String minDate, boolean isMinDate, boolean isDOB) {
       String dateReturn="";

        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd HH:mm:ss"; //In which you need put here
                String myFormatTwo = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                SimpleDateFormat sdfTwo = new SimpleDateFormat(myFormatTwo, Locale.US);
                if (tv instanceof TextView) {
                    TextView textView = (TextView) tv;
                    textView.setText(sdfTwo.format(myCalendar.getTime()));
                    textView.setTextColor(Color.GRAY);
                }

            }

        };
        Date d = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            if (minDate != null) {
                d = sdf.parse(minDate);
            } else {
                Calendar cal = Calendar.getInstance();
                if (isDOB) {
//                        cal.add(Calendar.DAY_OF_MONTH, 0);
                } else
                    cal.add(Calendar.DAY_OF_MONTH, 1);


                   /* String strDate = "2000-05-15T10:00:00-0700";
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                    Date dd = dateFormat.parse(strDate);
                    System.out.println(date);*/
                d = cal.getTime();
            }

        } catch (Exception e) {
            e.fillInStackTrace();
        }
        DatePickerDialog datePicker = new DatePickerDialog(context, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        if (isDOB) {
            datePicker.getDatePicker().setMaxDate(d.getTime());
        } else {
            if (isMinDate)

                datePicker.getDatePicker().setMinDate(d.getTime());

        }
        datePicker.show();
        return dateReturn;
    }









    public static String getMimeType(String url, Uri selUri, Context context) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        if(type==null){
            String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.MIME_TYPE};

            Cursor cursor  = context.getContentResolver().query(selUri, columns, null, null, null);
            cursor.moveToFirst();

            int mimeTypeColumnIndex = cursor.getColumnIndex(columns[1]);

            type = cursor.getString(mimeTypeColumnIndex);
            cursor.close();
        }
        return type;
    }




}
