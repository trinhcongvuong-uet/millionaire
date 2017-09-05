package com.t3h.Millionaire.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wrongway on 11/01/2016.
 */
public class DBManager {

    private Context context;
    private SQLiteDatabase sqLDB;
    private ArrayList<QuestionItem> arrQuestions = new ArrayList<QuestionItem>();
    private ArrayList<QuestionItem> arrQuestionsChange = new ArrayList<QuestionItem>();

    private final static String DB_PATH = Environment.getDataDirectory().getPath()+"/data/com.t3h.Millionaire/databases";
    private static final String DB_NAME = "Question";

    public DBManager(Context context){
        this.context = context;
        copyDB();
        getQuestion();
        getQuestionChange();
    }

    public ArrayList<QuestionItem> getArrQuestions(){
        return arrQuestions;
    }

    public ArrayList<QuestionItem> getArrQuestionsChange() {
        return arrQuestionsChange;
    }

    public void openDB(){
        String path = DB_PATH+"/"+DB_NAME;
        if(sqLDB==null||sqLDB.isOpen()==false){
            sqLDB = SQLiteDatabase.openDatabase(DB_PATH+"/"+DB_NAME,null,SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDB(){
        if(sqLDB!=null&&sqLDB.isOpen()){
            sqLDB.close();
            sqLDB = null;
        }
    }

    public void getQuestion(){
        arrQuestions.clear();
        //Mo DB
        openDB();
        Cursor cursor = sqLDB.rawQuery(SQLConst.SQL_GET_QUESTION,null);
        if(cursor==null){
            return;
        }
        QuestionItem questionItem;
        int indexId,indexQuestion,indexCaseA,indexCaseB,indexCaseC,indexCaseD,indexCaseTrue;

        indexId = cursor.getColumnIndex("_id");
        indexQuestion = cursor.getColumnIndex("Question");
        indexCaseA = cursor.getColumnIndex("CaseA");
        indexCaseB = cursor.getColumnIndex("CaseB");
        indexCaseC = cursor.getColumnIndex("CaseC");
        indexCaseD = cursor.getColumnIndex("CaseD");
        indexCaseTrue = cursor.getColumnIndex("TrueCase");

        String question,caseA,caseB,caseC,caseD;
        int id,caseTrue;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            id = cursor.getInt(indexId);
            question = cursor.getString(indexQuestion);
            caseA = cursor.getString(indexCaseA);
            caseB = cursor.getString(indexCaseB);
            caseC = cursor.getString(indexCaseC);
            caseD = cursor.getString(indexCaseD);
            caseTrue = cursor.getInt(indexCaseTrue);

            questionItem = new QuestionItem(id,question,caseA,caseB,caseC,caseD,caseTrue);
            arrQuestions.add(questionItem);
            cursor.moveToNext();
        }
    }

    public void getQuestionChange(){
        arrQuestionsChange.clear();
        //Mo DB
        openDB();
        Cursor cursor = sqLDB.rawQuery(SQLConst.SQL_GET_QUESTION,null);
        if(cursor==null){
            return;
        }
        QuestionItem questionItem;
        int indexId,indexQuestion,indexCaseA,indexCaseB,indexCaseC,indexCaseD,indexCaseTrue;

        indexId = cursor.getColumnIndex("_id");
        indexQuestion = cursor.getColumnIndex("Question");
        indexCaseA = cursor.getColumnIndex("CaseA");
        indexCaseB = cursor.getColumnIndex("CaseB");
        indexCaseC = cursor.getColumnIndex("CaseC");
        indexCaseD = cursor.getColumnIndex("CaseD");
        indexCaseTrue = cursor.getColumnIndex("TrueCase");

        String question,caseA,caseB,caseC,caseD;
        int id,caseTrue;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            id = cursor.getInt(indexId);
            question = cursor.getString(indexQuestion);
            caseA = cursor.getString(indexCaseA);
            caseB = cursor.getString(indexCaseB);
            caseC = cursor.getString(indexCaseC);
            caseD = cursor.getString(indexCaseD);
            caseTrue = cursor.getInt(indexCaseTrue);

            questionItem = new QuestionItem(id,question,caseA,caseB,caseC,caseD,caseTrue);
            arrQuestionsChange.add(questionItem);
            cursor.moveToNext();
        }
    }

    private void copyDB(){
        new File(DB_PATH).mkdirs();
        File file = new File(DB_PATH+"/"+DB_NAME);
        if(file.exists()){
            return;
        }

        try {
            file.createNewFile();
            InputStream inputStream = context.getAssets().open(DB_NAME);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int lenght;
            byte buff[] = new byte[1024];
            String result = "";
            while ((lenght = inputStream.read(buff))>0){
                fileOutputStream.write(buff,0,lenght);
            }
            inputStream.close();
            fileOutputStream.close();
            Log.i("COPY","DB is Copy");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
