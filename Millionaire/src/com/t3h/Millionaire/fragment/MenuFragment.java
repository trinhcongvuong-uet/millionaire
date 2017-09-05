package com.t3h.Millionaire.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.t3h.Millionaire.Activity.MyActivity;
import com.t3h.Millionaire.R;

/**
 * Created by Wrongway on 12/01/2016.
 */
public class MenuFragment extends BaseFragment implements View.OnClickListener {
    private Button btn_Play,btn_Instruction,btn_HightScore,btn_exit_game;
    public MenuFragment(int idLayout) {
        super(idLayout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        initViews();
        return rootView;
    }

    private void initViews() {
        btn_Play = (Button)rootView.findViewById(R.id.btn_play_game);
        btn_Play.setOnClickListener(this);
        btn_Instruction = (Button)rootView.findViewById(R.id.btn_instruction);
        btn_Instruction.setOnClickListener(this);
        btn_HightScore = (Button)rootView.findViewById(R.id.btn_hightscore);
        btn_HightScore.setOnClickListener(this);
        btn_exit_game = (Button)rootView.findViewById(R.id.btn_exit_game);
        btn_exit_game.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play_game:
                ((MyActivity)getActivity()).playGameApplication();
                break;
            case R.id.btn_instruction:
                break;
            case R.id.btn_hightscore:
                break;
            case R.id.btn_exit_game:
                ((MyActivity)getActivity()).finishApplication();
                break;
        }

    }
}
