package com.t3h.Millionaire.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.t3h.Millionaire.R;

/**
 * Created by Wrongway on 22/02/2016.
 */
public class HelpPeopleDialog extends Dialog implements View.OnClickListener {

    private LinearLayout caseA,caseB,caseC,caseD;
    private TextView help_people_dismiss;
    private int randA,randB,randC,randD;
//    private int result;

    public HelpPeopleDialog(Context context,int randA,int randB,int randC,int randD) {
        super(context);
//        this.result = result;
        this.randA = randA;
        this.randB = randB;
        this.randC = randC;
        this.randD = randD;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.helppeoplelayout);
        initViews();
    }

    private void initViews() {
        caseA = (LinearLayout)findViewById(R.id.help_people_caseA);
        caseB = (LinearLayout)findViewById(R.id.help_people_caseB);
        caseC = (LinearLayout)findViewById(R.id.help_people_caseC);
        caseD = (LinearLayout)findViewById(R.id.help_people_caseD);

        help_people_dismiss = (TextView) findViewById(R.id.tv_help_people_dismiss);
        help_people_dismiss.setOnClickListener(this);
        setParams(randA,randB,randC,randD);
    }

    public void setParams(int randA,int randB,int randC,int randD){
        LinearLayout.LayoutParams paramsA = (LinearLayout.LayoutParams)caseA.getLayoutParams();
        paramsA.width = randA;
        caseA.setLayoutParams(paramsA);

        LinearLayout.LayoutParams paramsB = (LinearLayout.LayoutParams)caseB.getLayoutParams();
        paramsB.width = randB;
        caseB.setLayoutParams(paramsB);

        LinearLayout.LayoutParams paramsC = (LinearLayout.LayoutParams)caseC.getLayoutParams();
        paramsC.width = randC;
        caseC.setLayoutParams(paramsC);

        LinearLayout.LayoutParams paramsD = (LinearLayout.LayoutParams)caseD.getLayoutParams();
        paramsD.width = randD;
        caseD.setLayoutParams(paramsD);
//Log.i("jjjjjjjjjj",randA+"");
//        caseA.getLayoutParams().width = randA;
//        caseB.getLayoutParams().width = randB;
//        caseC.getLayoutParams().width = randC;
//        caseD.getLayoutParams().width = randD;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
