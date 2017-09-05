package com.t3h.Millionaire.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Wrongway on 12/01/2016.
 */
public class BaseFragment extends Fragment {
    protected View rootView;
    protected int idLayout;

    public BaseFragment(int idLayout){
        this.idLayout = idLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(idLayout,container,false);
        return rootView;
    }
}
