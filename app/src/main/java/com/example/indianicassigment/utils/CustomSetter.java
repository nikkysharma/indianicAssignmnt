package com.example.indianicassigment.utils;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomSetter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(CircleImageView view, String url) {

        Glide.with(view.getContext()).load(url).into(view);
    }
}
