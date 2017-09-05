package com.t3h.Millionaire.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.t3h.Millionaire.Activity.MyActivity;
import com.t3h.Millionaire.Adapter.ListViewAdapter;
import com.t3h.Millionaire.Data.DBManager;
import com.t3h.Millionaire.Dialog.FinishDialog;
import com.t3h.Millionaire.Dialog.HelpPeopleDialog;
import com.t3h.Millionaire.Dialog.MyDialog;
import com.t3h.Millionaire.R;
import com.t3h.Millionaire.User.Account;
import com.t3h.Millionaire.User.User;

import java.util.Random;

/**
 * Created by Wrongway on 13/01/2016.
 */
public class PlayGameFragment extends BaseFragment implements View.OnClickListener {
    private static final int OPEN_DRAWERLAYOUT = 1;



    DrawerLayout drawerLayout;
    private ListView lv_money_landmark;
    ListViewAdapter listViewAdapter;
    private Context context;

    private TextView tv_content_question,tv_number_question;
    private TextView tv_score;
    private Button btn_help_5050,btn_help_people,btn_help_stop_game,btn_help_phone,btn_help_change_question;
    private Button btn_listview_close;
    private Boolean is_help_5050,is_help_people,is_help_phone,is_help_change_question;
    private Boolean isCaseA,isCaseB,isCaseC,isCaseD;
    private TextView tv_caseA,tv_caseB,tv_caseC,tv_caseD;
    private DBManager dbManager;
    private User myUser;
    private int numberQuestion;
    private MyDialog myDialog;
    private FinishDialog finishDialog;
    private HelpPeopleDialog helpPeopleDialog;
    private boolean isCorrect = false;
    private Account myAccount;

    public PlayGameFragment(int idLayout, Context context) {
        super(idLayout);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        initViews();
        return rootView;
    }

    private void initViews() {
//        user
        myUser = new User();
//        Account
        myAccount = new Account();
//        numberquestion of myUser
        numberQuestion = 1;
//        help is true
        is_help_5050 = true;
        is_help_change_question = true;
        is_help_people = true;
        is_help_phone = true;
//        data
        dbManager = new DBManager(context);
//        score
        tv_score = (TextView)rootView.findViewById(R.id.tv_score);

//        five help
        btn_help_5050 =(Button)rootView.findViewById(R.id.btn_help_5050);
        btn_help_5050.setOnClickListener(this);
        btn_help_change_question = (Button)rootView.findViewById(R.id.btn_help_change_question);
        btn_help_change_question.setOnClickListener(this);
        btn_help_people = (Button)rootView.findViewById(R.id.btn_help_people);
        btn_help_people.setOnClickListener(this);
        btn_help_phone = (Button)rootView.findViewById(R.id.btn_help_phone);
        btn_help_phone.setOnClickListener(this);
        btn_help_stop_game = (Button)rootView.findViewById(R.id.btn_help_stop_game);
        btn_help_stop_game.setOnClickListener(this);
//        question
        tv_number_question = (TextView)rootView.findViewById(R.id.tv_number_question);
        tv_content_question = (TextView)rootView.findViewById(R.id.tv_content_question);
//kiem tra help da su dung chua
        is_help_5050 = true;
        is_help_change_question = true;
        is_help_people = true;
        is_help_phone = true;
//        four case
        tv_caseA = (TextView)rootView.findViewById(R.id.tv_caseA);
        tv_caseA.setOnClickListener(this);
        tv_caseB = (TextView)rootView.findViewById(R.id.tv_caseB);
        tv_caseB.setOnClickListener(this);
        tv_caseC = (TextView)rootView.findViewById(R.id.tv_caseC);
        tv_caseC.setOnClickListener(this);
        tv_caseD = (TextView)rootView.findViewById(R.id.tv_caseD);
        tv_caseD.setOnClickListener(this);

        btn_listview_close = (Button)rootView.findViewById(R.id.btn_listview_close);
        btn_listview_close.setOnClickListener(this);

        drawerLayout = (DrawerLayout)rootView.findViewById(R.id.view);
        lv_money_landmark = (ListView)rootView.findViewById(R.id.lv_money_landmark);

        listViewAdapter = new ListViewAdapter(context);
        lv_money_landmark.setAdapter(listViewAdapter);
        openDrawerLayout();
        setQuestionAndCase();
    }

    public void setBackgroundCaseTrue(int i){
        switch (i){
            case 1:
                tv_caseA.setBackgroundResource(R.drawable.case_true_bg);
                break;
            case 2:
                tv_caseB.setBackgroundResource(R.drawable.case_true_bg);
                break;
            case 3:
                tv_caseC.setBackgroundResource(R.drawable.case_true_bg);
                break;
            case 4:
                tv_caseD.setBackgroundResource(R.drawable.case_true_bg);
                break;
            default:
                break;
        }
    }

    public void setBackgroundChooseCase(String myCase){
        switch (myCase){
            case "A":
                tv_caseA.setBackgroundResource(R.drawable.case_choose_bg);
                break;
            case "B":
                tv_caseB.setBackgroundResource(R.drawable.case_choose_bg);
                break;
            case "C":
                tv_caseC.setBackgroundResource(R.drawable.case_choose_bg);
                break;
            case "D":
                tv_caseD.setBackgroundResource(R.drawable.case_choose_bg);
                break;
            default:
                break;
        }
    }

    public void setQuestionAndCase(){
        //tranh truong hop cau truoc su dung 50_50
        afterHelp50_50();
        //        is Case is true
        isCaseA = true;
        isCaseB = true;
        isCaseC = true;
        isCaseD = true;
//        show moneylandmark
        openDrawerLayout();
//        setQuestion
        tv_number_question.setText("Câu " + numberQuestion);
        tv_content_question.setText(dbManager.getArrQuestions().get(numberQuestion-1).getQuestion());
//   set case
        tv_caseA.setText(dbManager.getArrQuestions().get(numberQuestion-1).getCaseA());
        tv_caseA.setBackgroundResource(R.drawable.case_bg);
        tv_caseB.setText(dbManager.getArrQuestions().get(numberQuestion-1).getCaseB());
        tv_caseB.setBackgroundResource(R.drawable.case_bg);
        tv_caseC.setText(dbManager.getArrQuestions().get(numberQuestion-1).getCaseC());
        tv_caseC.setBackgroundResource(R.drawable.case_bg);
        tv_caseD.setText(dbManager.getArrQuestions().get(numberQuestion-1).getCaseD());
        tv_caseD.setBackgroundResource(R.drawable.case_bg);
    }

    public int convertCase(String myCase){
        int result = 0;
        switch (myCase){
            case "A":
                result = 1;
                break;
            case "B":
                result = 2;
                break;
            case "C":
                result = 3;
                break;
            case "D":
                result = 4;
                break;
            default:
                break;
        }
        return result;
    }

    public String convertCase(int myCase){
        String result = "";
        switch (myCase){
            case 1:
                result = "A";
                break;
            case 2:
                result = "B";
                break;
            case 3:
                result = "C";
                break;
            case 4:
                result = "D";
                break;
        }
        return result;
    }

    public void corectAnswer(String myCase){
        int result = convertCase(myCase);
        setBackgroundCaseTrue(dbManager.getArrQuestions().get(numberQuestion-1).getCaseTrue());

        if(result==dbManager.getArrQuestions().get(numberQuestion-1).getCaseTrue()){
            isCorrect = true;
            new Handler().postDelayed(runwaitchangle,5000);
        }
        else{
            //cau tra loi sai
            isFalseAnswer();
        }
    }

    public void isCorrectAnswer(){
//        new Handler().postDelayed(runwaitchangle,5000);
        myUser.setUsermoney(listViewAdapter.getArrayMoneyLandmark().get(15-numberQuestion));
        tv_score.setText(myUser.getUsermoney()+"$");
        numberQuestion++;
        if(numberQuestion<16){
            setQuestionAndCase();
        } else{
            //ket thuc 15 cau
            finishDialog = new FinishDialog(context,this,150000000,FinishDialog.TYPE_FINISH_HELP_STOP_GAME);
            finishDialog.show();
        }
    }

    public void isFalseAnswer(){
        new Handler().postDelayed(runagainmenufragment,5000);
    }


    public void openDrawerLayout(){
        if(!drawerLayout.isDrawerOpen(Gravity.LEFT)){
            drawerLayout.openDrawer(Gravity.LEFT);
            new Handler().postDelayed(runnable,5000);
        }
    }

    public void afterHelp50_50(){
        tv_caseA.setVisibility(View.VISIBLE);
        tv_caseB.setVisibility(View.VISIBLE);
        tv_caseC.setVisibility(View.VISIBLE);
        tv_caseD.setVisibility(View.VISIBLE);
    }

//    tro giup 50 50
    Random rd = new Random();
    public void help_50_50(int Correct){
        int itemcase = rd.nextInt(3);
        switch (Correct){
            //dap an A dung
            case 1:
                switch (itemcase){
                    case 0:
                        //bo C,D
                        tv_caseC.setVisibility(View.INVISIBLE);
                        tv_caseD.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                    case 1:
                        //bo B,D
                        tv_caseB.setVisibility(View.INVISIBLE);
                        tv_caseD.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                    case 2:
                        //bo B,C
                        tv_caseB.setVisibility(View.INVISIBLE);
                        tv_caseC.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                }
                //dap an B dung
            case 2:
                switch (itemcase){
                    case 0:
                        //bo C,D
                        tv_caseC.setVisibility(View.INVISIBLE);
                        tv_caseD.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                    case 1:
                        //bo A,C
                        tv_caseC.setVisibility(View.INVISIBLE);
                        tv_caseA.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                    case 2:
                        //bo A,D
                        tv_caseA.setVisibility(View.INVISIBLE);
                        tv_caseD.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                }
                //dap an C dung
            case 3:
                switch (itemcase){
                    case 0:
                        //bo A,B
                        tv_caseA.setVisibility(View.INVISIBLE);
                        tv_caseB.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                    case 1:
                        //bo A,D
                        tv_caseD.setVisibility(View.INVISIBLE);
                        tv_caseA.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                    case 2:
                        //bo B,D
                        tv_caseB.setVisibility(View.INVISIBLE);
                        tv_caseD.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                }
                //dap an D dung
            case 4:
                switch (itemcase){
                    case 0:
                        //bo A,B
                        tv_caseA.setVisibility(View.INVISIBLE);
                        tv_caseB.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                    case 1:
                        //bo A,C
                        tv_caseC.setVisibility(View.INVISIBLE);
                        tv_caseA.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                    case 2:
                        //bo B,C
                        tv_caseB.setVisibility(View.INVISIBLE);
                        tv_caseC.setVisibility(View.INVISIBLE);
                        is_help_5050 = false;
                        btn_help_5050.setBackgroundResource(R.drawable.logo_not5050);
                        return;
                }
        }
    }

    public void helpChangeQuestion(){
        //tranh truong hop truoc do da su dung 50_50
        afterHelp50_50();
        btn_help_change_question.setBackgroundResource(R.drawable.logo_notdoicauhoi);
        is_help_change_question = false;
        tv_content_question.setText(dbManager.getArrQuestionsChange().get(numberQuestion-1).getQuestion());
        tv_caseA.setText(dbManager.getArrQuestionsChange().get(numberQuestion-1).getCaseA());
        tv_caseB.setText(dbManager.getArrQuestionsChange().get(numberQuestion-1).getCaseB());
        tv_caseC.setText(dbManager.getArrQuestionsChange().get(numberQuestion-1).getCaseC());
        tv_caseD.setText(dbManager.getArrQuestionsChange().get(numberQuestion-1).getCaseD());
    }

    public void helpPeople(int Correct){
        int rand = rd.nextInt(10);
        int A,B,C,D;
        if(rand == 0){
            switch (Correct){
                case 1:
                    A = rd.nextInt(100);
                    B = rd.nextInt(100);
                    C = rd.nextInt(100);
                    D = 300 - A - B - C;
                    helpPeopleDialog = new HelpPeopleDialog(context,A,B,C,D);
                    helpPeopleDialog.show();
                    break;
                case 2:
                    A = rd.nextInt(100);
                    B = rd.nextInt(100);
                    C = rd.nextInt(100);
                    D = 300 - A - B - C;
                    helpPeopleDialog = new HelpPeopleDialog(context,A,B,C,D);
                    helpPeopleDialog.show();
                    break;
                case 3:
                    A = rd.nextInt(100);
                    B = rd.nextInt(100);
                    C = rd.nextInt(100);
                    D = 300 - A - B - C;
                    helpPeopleDialog = new HelpPeopleDialog(context,A,B,C,D);
                    helpPeopleDialog.show();
                    break;
                case 4:
                    A = rd.nextInt(100);
                    B = rd.nextInt(100);
                    D = rd.nextInt(100);
                    C = 300 - A - B - D;
                    helpPeopleDialog = new HelpPeopleDialog(context,A,B,C,D);
                    helpPeopleDialog.show();
                    break;
                default:
                    break;
            }
        }
        else{
            switch (Correct){
                case 1:
                    A = rd.nextInt(50)+100;//max 150
                    B = rd.nextInt(70);//max 70
                    C = rd.nextInt(70);//max 70
                    D = 300 - A - B - C;
                    helpPeopleDialog = new HelpPeopleDialog(context,A,B,C,D);
                    helpPeopleDialog.show();
                    break;
                case 2:
                    A = rd.nextInt(70);//max 70
                    B = rd.nextInt(50)+100;//max 150
                    C = rd.nextInt(70);//max 70
                    D = 300 - A - B - C;
                    helpPeopleDialog = new HelpPeopleDialog(context,A,B,C,D);
                    helpPeopleDialog.show();
                    break;
                case 3:
                    A = rd.nextInt(70);//max 70
                    B = rd.nextInt(70);//max 70
                    C = rd.nextInt(50)+100;//max 150
                    D = 300 - A - B - C;
                    helpPeopleDialog = new HelpPeopleDialog(context,A,B,C,D);
                    helpPeopleDialog.show();
                    break;
                case 4:
                    A = rd.nextInt(70);//max 70
                    B = rd.nextInt(70);//max 70
                    D = rd.nextInt(50)+100;//max 150
                    C = 300 - A - B - D;
                    helpPeopleDialog = new HelpPeopleDialog(context,A,B,C,D);
                    helpPeopleDialog.show();
                    break;
                default:
                    break;
            }
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(drawerLayout.isDrawerOpen(Gravity.LEFT)){
                drawerLayout.closeDrawer(Gravity.LEFT);
            }
        }
    };

    Runnable runwaitchangle = new Runnable() {
        @Override
        public void run() {
            if(isCorrect == true){
                isCorrect = false;
                isCorrectAnswer();
            }
        }
    };

    Runnable runhelppeople = new Runnable() {
        @Override
        public void run() {
        }
    };

    Runnable runagainmenufragment = new Runnable() {
        @Override
        public void run() {
//            ((MyActivity)getActivity()).showAgainMenuFragment();
            int money = 0;
            if(myUser.getUsermoney()>=2000000){
                if(myUser.getUsermoney()<22000000){
                    money = 2000000;
                }
                else{
                    if(myUser.getUsermoney()>=22000000&&myUser.getUsermoney()<150000000){
                        money = 22000000;
                    }
                    else{
                        money = 150000000;
                    }
                }
            }
            if (money==0){
                ((MyActivity)getActivity()).showAgainMenuFragment();
            }
            else{
                finishDialog = new FinishDialog(context,PlayGameFragment.this,money,FinishDialog.TYPE_FINISH_FALSE_CASE);
                finishDialog.show();
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_caseA:
                if(isCaseA){
                    myDialog = new MyDialog(context,this,MyDialog.CHOOSE_CASE);
                    myDialog.showDialog("A");
                    isCaseA = false;
                }
                break;
            case R.id.tv_caseB:
                if(isCaseB){
                    myDialog = new MyDialog(context,this,MyDialog.CHOOSE_CASE);
                    myDialog.showDialog("B");
                    isCaseB = false;
                }
                break;
            case R.id.tv_caseC:
                if(isCaseC){
                    myDialog = new MyDialog(context,this,MyDialog.CHOOSE_CASE);
                    myDialog.showDialog("C");
                    isCaseC = false;
                }
                break;
            case R.id.tv_caseD:
                if(isCaseD){
                    myDialog = new MyDialog(context,this,MyDialog.CHOOSE_CASE);
                    myDialog.showDialog("D");
                    isCaseD = false;
                }
                break;
            case R.id.btn_listview_close:
                drawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.btn_help_5050:
                if(is_help_5050){
                    myDialog = new MyDialog(context,this,MyDialog.HELP_50_50);
                    myDialog.showDialog(convertCase(dbManager.getArrQuestions().get(numberQuestion-1).getCaseTrue()));
                }
                break;
            case R.id.btn_help_change_question:
                if(is_help_change_question){
                    myDialog = new MyDialog(context,this,MyDialog.HELP_CHANGE_QUESTION);
                    myDialog.showDialog(null);
                }
                break;
            case R.id.btn_help_people:
                if(is_help_people){
                    myDialog = new MyDialog(context,this,MyDialog.HELP_PEOPLE);
                    myDialog.showDialog(convertCase(dbManager.getArrQuestions().get(numberQuestion-1).getCaseTrue()));
                }
                break;
            case R.id.btn_help_phone:
                if(is_help_phone){
                    myDialog = new MyDialog(context,this,MyDialog.HELP_PHONE);
                    myDialog.showDialog(null);
                }
                break;
            case R.id.btn_help_stop_game:
                finishDialog = new FinishDialog(context,this,myUser.getUsermoney(),FinishDialog.TYPE_FINISH_HELP_STOP_GAME);
                finishDialog.show();
                break;
        }
    }
}
