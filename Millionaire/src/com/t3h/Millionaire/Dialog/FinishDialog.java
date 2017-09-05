package com.t3h.Millionaire.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.t3h.Millionaire.Activity.MyActivity;
import com.t3h.Millionaire.R;
import com.t3h.Millionaire.User.Account;
import com.t3h.Millionaire.User.User;
import com.t3h.Millionaire.fragment.PlayGameFragment;

/**
 * Created by Wrongway on 15/01/2016.
 */
public class FinishDialog extends Dialog implements View.OnClickListener {
    private Button btn_dialog_finish_save,btn_dialog_finish_cancel;
    private TextView tv_dialog_finish_money;
    private EditText edt_dialog_finish_username;
    private PlayGameFragment fragment;
    private int usermoney;
    private int type_finish;
    private Context context;

    public static final int TYPE_FINISH_FALSE_CASE = 0;
    public static final int TYPE_FINISH_HELP_STOP_GAME = 1;

    public FinishDialog(Context context,PlayGameFragment fragment,int usermoney,int type_finish) {
        super(context);
        this.context = context;
        this.fragment = fragment;
        this.usermoney = usermoney;
        this.type_finish = type_finish;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.finishlayout);
        initViews();
    }

    private void initViews() {
        btn_dialog_finish_save = (Button)findViewById(R.id.btn_dialog_finish_save);
        btn_dialog_finish_save.setOnClickListener(this);
        btn_dialog_finish_cancel = (Button)findViewById(R.id.btn_dialog_finish_cancel);
        btn_dialog_finish_cancel.setOnClickListener(this);
        tv_dialog_finish_money = (TextView)findViewById(R.id.tv_dialog_finish_money);
        edt_dialog_finish_username = (EditText)findViewById(R.id.edt_dialog_finish_username);
        tv_dialog_finish_money.setText(usermoney+"");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dialog_finish_save:
                fragment.isFalseAnswer();
                User user = new User(edt_dialog_finish_username.getText().toString(),
                        Integer.parseInt(tv_dialog_finish_money.getText().toString()));
                Account account = new Account();
                account.updateAccount(user.getUsername(),user.getUsermoney());
                ((MyActivity)context).showAgainMenuFragment();
                dismiss();
                break;
            case R.id.btn_dialog_finish_cancel:
                dismiss();
                if(type_finish==TYPE_FINISH_FALSE_CASE)
                    fragment.isFalseAnswer();
                break;
        }
    }
}
