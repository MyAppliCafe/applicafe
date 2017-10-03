package com.appdockproject.appdock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.appdockproject.appdock.Fragments.appPageFragment;
import com.appdockproject.appdock.Fragments.devFragment;
import com.appdockproject.appdock.Fragments.eduFragment;
import com.appdockproject.appdock.Fragments.facebookFragment;
import com.appdockproject.appdock.Fragments.feedbackFragment;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends FragmentActivity {

    String TAG = "fragments";
    Button devBtn, eduBtn, cmntBtn, appBtn, facebookBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        // Setup firebase to cache data locally
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        hideSystemUI();

        devBtn = (Button) findViewById(R.id.devBtn);
        eduBtn = (Button) findViewById(R.id.eduBtn);
        //cmntBtn = (Button) findViewById(R.id.comBtn); // Removed survey
        appBtn = (Button) findViewById(R.id.appBtn);
        facebookBtn = (Button) findViewById(R.id.fbBtn);

        devBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i(TAG, "Pressed Dev");
                resetButtons();
                devBtn.setPressed(true);
                changeFragment(new devFragment());
            }
        });

        eduBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i(TAG, "Pressed Edu");
                resetButtons();
                eduBtn.setPressed(true);
                changeFragment(new eduFragment());
            }
        });
/*
        cmntBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i(TAG, "Pressed Cmnt");
                resetButtons();
                cmntBtn.setPressed(true);
                changeFragment(new feedbackFragment());
            }
        });
*/
        appBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i(TAG, "Pressed App");
                resetButtons();
                appBtn.setPressed(true);
                changeFragment(new appPageFragment());
            }
        });
        facebookBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i(TAG, "Pressed Facebook");
                resetButtons();
                facebookBtn.setPressed(true);
                changeFragment(new facebookFragment());
            }
        });


        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            appPageFragment firstFragment = new appPageFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }

    private void resetButtons(){
        devBtn.setPressed(false);
        eduBtn.setPressed(false);
        //cmntBtn.setPressed(false);
        appBtn.setPressed(false);
        facebookBtn.setPressed(false);
    }

    public void changeFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        Log.i(TAG, "Hiding UI");
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    // Needs this to remove statusbar and navigation layout after focus of activity regains focus
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Log.i(TAG, "Focus changed, hiding ui");
            hideSystemUI();
        }
    }

}
