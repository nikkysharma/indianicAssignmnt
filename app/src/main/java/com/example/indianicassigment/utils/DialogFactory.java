package com.example.indianicassigment.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.example.indianicassigment.R;
import com.example.indianicassigment.data.manager.DataManager;
import com.example.indianicassigment.view.activity.auth.AuthActivity;
import com.example.indianicassigment.view.base.BaseActivity;

import java.util.Objects;

public class DialogFactory {
    public static void showErrorSneaker(Context context, String msg) {

        Sneaker.with((Activity) context)
                .setTitle("Error!")
                .setMessage(msg+".")
                .setTypeface(Typeface.createFromAsset(context.getAssets(), "font/OpenSans-Light.ttf"))
                .sneakError();
        vibrate(context, 500);
//        speak(msg);
        // YoYo.with(Techniques.Shake).duration(500).playOn(view);
    }

    public static void vibrate(Context context, long millis) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(millis, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(millis);
        }
    }
    private static Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }
    public static  void twoButtonFancyDialoge(Activity context, String message){
        new FancyDialog.Builder(context)
                .setTitle("Online Shopping")
                .setMessage(message)
                .setPositiveBtnText("Ok")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Cancel")
                .setNegativeBtnBackground("#c1272d")
                // .setGifResource(R.drawable.alert_icon)      //     //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new FancyDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .OnNegativeClicked(new FancyDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .build();
    }
    public static void fancyAlert(Activity context, String message){
        vibrate(context,500);
        new FancyDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.app_name))
                .setMessage(message)
                .setPositiveBtnText("OK")
                .setPositiveBtnBackground("#FBD04D")
                //  .setGifResource(R.drawable.alert_icon)      //pass your gif, png or jpg
                .isCancellable(true)
                .OnPositiveClicked(new FancyDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .build();

    }





    public static Dialog createSimpleOkErrorDialog(Context context,
                                                   @StringRes int titleResource,
                                                   @StringRes int messageResource) {

        return createSimpleOkErrorDialog(context,
                context.getString(titleResource),
                context.getString(messageResource));
    }

    private static Dialog createGenericErrorDialog(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

//    public static void createDialog(Context context, String message) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
//                 .setTitle("Authentic")
//                // .setTitle(context.getString(R.string.dialog_error_title))
//                .setMessage(message)
//                .setNeutralButton(R.string.dialog_action_ok, null);
//        alertDialog.create().show();
//    }

    //    public static void createDialog(Context context, String message) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
//                 .setTitle("Authentic")
//                // .setTitle(context.getString(R.string.dialog_error_title))
//                .setMessage(message)
//                .setNeutralButton(R.string.dialog_action_ok, null);
//        alertDialog.create().show();
//    }
    public static void createDialog(Context context, String message) {
//        context = AppManager.getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_alert_two, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.transparent);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        TextView tvMessage = view.findViewById(R.id.textView_dialog);
        TextView btnOk = view.findViewById(R.id.button_dialog);
        if (message.length() > 0) {
            tvMessage.setText(message);
        }
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    public static void createDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                // .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        alertDialog.create().show();
    }


    public static Dialog createGenericErrorDialog(Context context, @StringRes int messageResource) {
        return createGenericErrorDialog(context, context.getString(messageResource));
    }

    public static Dialog dialogTransparent(Activity mActivity, int layout) {

        Dialog dialog = new Dialog(mActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(layout);
        dialog.setCancelable(true);

        return dialog;
    }

    private static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.please_wait_text));
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context,
                                                      @StringRes int messageResource) {
        return createProgressDialog(context, context.getString(messageResource));
    }



    public static Dialog createDialog(Activity mActivity, int layoutResID) {

        Dialog dialog = new Dialog(mActivity, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layoutResID);

        Window window = dialog.getWindow();
        if (window != null) {
            window.getAttributes().windowAnimations = R.style.DialogAnimation_2;
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawableResource(R.color.bg_color);
        }
        dialog.show();

        return dialog;
    }
    public static void showLogoutDialog(Activity context,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_alert_logout, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(R.color.transparent);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        TextView tvMessage = view.findViewById(R.id.textView_dialog);
        Button btnNo = view.findViewById(R.id.button_dialog_cancel);
        Button btnYes = view.findViewById(R.id.button_dialog);
        if (message.length() > 0) {
            tvMessage.setText(message);
        }
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                DataManager mDataManager = ((BaseActivity)context).getMyViewModel().getDataManager();
                mDataManager.setBooleanData(DBConstants.IS_LOGGED_IN, false);
                Intent intent = new Intent(context, AuthActivity.class);
                context.startActivity(intent);
                context.finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
