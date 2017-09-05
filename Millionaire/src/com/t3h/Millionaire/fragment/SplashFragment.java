package com.t3h.Millionaire.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.t3h.Millionaire.Activity.MyActivity;

/**
 * Created by Wrongway on 12/01/2016.
 */
public class SplashFragment extends BaseFragment {
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(isAdded()){
                ((MyActivity)getActivity()).showMenuFragment();
            }
        }
    };

    public SplashFragment(int idLayout) {
        super(idLayout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView =  super.onCreateView(inflater, container, savedInstanceState);
        new Handler().postDelayed(runnable,5000);
        return rootView;
    }
}
