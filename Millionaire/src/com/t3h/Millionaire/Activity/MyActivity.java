package com.t3h.Millionaire.Activity;

import android.app.Activity;
import android.os.Bundle;
import com.t3h.Millionaire.R;
import com.t3h.Millionaire.fragment.MenuFragment;
import com.t3h.Millionaire.fragment.PlayGameFragment;
import com.t3h.Millionaire.fragment.SplashFragment;

public class MyActivity extends Activity{
    /**
     * Called when the activity is first created.
     */
    private SplashFragment splashFragment = new SplashFragment(R.layout.splashlayout);
    private MenuFragment menuFragment = new MenuFragment(R.layout.menulayout);
    private PlayGameFragment playGameGragment = new PlayGameFragment(R.layout.playlayout,this);



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();

    }

    private void initViews() {
        getFragmentManager().beginTransaction().
                replace(android.R.id.content,splashFragment).commit();
    }

    public void showMenuFragment() {
        getFragmentManager().beginTransaction().hide(splashFragment)
                .add(android.R.id.content,menuFragment).addToBackStack(null)
                .show(menuFragment).commit();
    }

    public void showAgainMenuFragment(){
        menuFragment = new MenuFragment(R.layout.menulayout);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,menuFragment).commit();
    }

    public void finishApplication() {
        finish();
    }

    public void playGameApplication() {
        getFragmentManager().beginTransaction().hide(menuFragment)
                .add(android.R.id.content,playGameGragment).addToBackStack(null)
                .show(playGameGragment).commit();
    }
}
