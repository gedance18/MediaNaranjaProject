package com.example.encuentratumedianaranja;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class profileFragment extends Fragment {

    private static final String ARG_PROFILE_IMAGE_URL = "profile_image_url";
    private static final String ARG_NAME = "name";
    private static final String ARG_AGE = "age";

    private String profileImageUrl;
    private String name;
    private String age;

    public static profileFragment newInstance(String profileImageUrl, String name, String age) {
        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROFILE_IMAGE_URL, profileImageUrl);
        args.putString(ARG_NAME, name);
        args.putString(ARG_AGE, age);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profileImageUrl = getArguments().getString(ARG_PROFILE_IMAGE_URL);
            name = getArguments().getString(ARG_NAME);
            age = getArguments().getString(ARG_AGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}