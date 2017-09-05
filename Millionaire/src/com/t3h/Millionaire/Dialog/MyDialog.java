package com.t3h.Millionaire.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.t3h.Millionaire.R;
import com.t3h.Millionaire.fragment.PlayGameFragment;

/**
 * Created by Wrongway on 14/01/2016.
 */
public class MyDialog extends Dialog implements View.OnClickListener {
    private Button btn_dialog_yes,btn_dialog_no,btn_dialod_cancel;
    private TextView tv_dialod_title,tv_dialog_content;
    public static final int CHOOSE_CASE = 0;
    public static final int HELP_50_50 = 1;
    public static final int HELP_PHONE = 2;
    public static final int HELP_CHANGE_QUESTION = 3;
    public static final int HELP_PEOPLE = 4;
    public static final int HELP_STOP_GAME = 5;

    private int type_dialog;
    private PlayGameFragment fragment;
    private String myCase;
    private int result;
    public MyDialog(Context context, PlayGameFragment fragment,int type_dialog) {
        super(context);
        this.type_dialog = type_dialog;
        this.fragment = fragment;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialoglayout);
        initViews();
    }

    private void initViews() {
        tv_dialod_title = (TextView)findViewById(R.id.tv_dialog_title);
        tv_dialog_content = (TextView)findViewById(R.id.tv_dialog_content);

        btn_dialog_yes = (Button)findViewById(R.id.btn_dialog_yes);
        btn_dialog_yes.setOnClickListener(this);

        btn_dialod_cancel = (Button)findViewById(R.id.btn_dialog_cacel);
        btn_dialod_cancel.setOnClickListener(this);

        btn_dialog_no = (Button)findViewById(R.id.btn_dialog_no);
        btn_dialog_no.setOnClickListener(this);
    }

    public void onClickwithTypeDialog(String myCase){
        switch (type_dialog){
            case CHOOSE_CASE:
                this.myCase = myCase;
                tv_dialod_title.setText("CASE FINAL");
                tv_dialog_content.setText("You want choose case " + myCase + " is case final?");
                break;
            case HELP_50_50:
                this.result = fragment.convertCase(myCase);
                tv_dialod_title.setText("HELP_50_50");
                tv_dialog_content.setText("You want use help 50:50?");
                break;
            case HELP_CHANGE_QUESTION:
                tv_dialod_title.setText("HELP CHANGE QUESTION");
                tv_dialog_content.setText("You want use help change question?");
                break;
            case HELP_PEOPLE:
                this.result = fragment.convertCase(myCase);
                tv_dialod_title.setText("HELP PEOPLE");
                tv_dialog_content.setText("You want use help people?");
                break;
            case HELP_PHONE:
                tv_dialod_title.setText("HELP PHONE");
                tv_dialog_content.setText("You want use help phone?");
                break;
            case HELP_STOP_GAME:
                tv_dialod_title.setText("HELP STOP GAME");
                tv_dialog_content.setText("You want use help stop game?");
                break;
        }
    }

    public void showDialog(String myCase){
        onClickwithTypeDialog(myCase);
        this.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dialog_yes:
                switch (type_dialog){
                    case CHOOSE_CASE:
                        fragment.setBackgroundChooseCase(myCase);
                        new Handler().postDelayed(runnableWatingResult,2000);
                        dismiss();
                        break;
                    case HELP_50_50:
                        fragment.help_50_50(result);
                        dismiss();
                        break;
                    case HELP_CHANGE_QUESTION:
                        fragment.helpChangeQuestion();
                        dismiss();
                        break;
                    case HELP_PEOPLE:
                        fragment.helpPeople(result);
                        dismiss();
                        break;
                    case HELP_PHONE:
                        dismiss();
                        break;
                    case HELP_STOP_GAME:
                        dismiss();
                        break;

                }
                break;
            case R.id.btn_dialog_no:
                dismiss();
                break;
            case R.id.btn_dialog_cacel:
                dismiss();
                break;
        }

    }

    private Runnable runnableWatingResult = new Runnable() {
        @Override
        public void run() {
            fragment.corectAnswer(myCase);
        }
    };
}
