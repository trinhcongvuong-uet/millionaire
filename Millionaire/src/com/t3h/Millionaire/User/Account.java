package com.t3h.Millionaire.User;

import android.os.Environment;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Wrongway on 26/12/2015.
 */
public class Account {
    private ArrayList<User> arrayAccount = new ArrayList<User>();
    private File myFile;
    private static final String DATAPATH = Environment.getDataDirectory().getPath()+"/data/com.t3h.Millionaire";

    public Account(){
        loadAccount();
    }

    public void loadAccount(){
        myFile = new File(DATAPATH+"/Account.txt");
        try {
            if (!myFile.exists()){
                myFile.createNewFile();
            }
            FileInputStream fr = new FileInputStream(myFile);
            BufferedReader br=new BufferedReader(new InputStreamReader(fr,"UTF8"));
            String line;
            while ((line = br.readLine())!=null){
                String[] data = line.split("//");
                User user = new User(data[0],Integer.parseInt(data[1]));
                arrayAccount.add(user);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(String usename,int usemoney){
        myFile = new File(DATAPATH+"/Account.txt");
        try {
            if(!myFile.exists()){
                myFile.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(myFile,false);
            String data = usename+"//"+usemoney+"\n";
            out.write(data.getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
