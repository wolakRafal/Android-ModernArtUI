package com.coursera.android.modernartui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView mVioletImageView = null;
    private ImageView mPinkImageView = null;
    private ImageView mRedImageView = null;
    private ImageView mBlueImageView = null;
    private DialogFragment mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVioletImageView = (ImageView) findViewById(R.id.VioletImageView);
        mPinkImageView = (ImageView) findViewById(R.id.PinkImageView);
        mRedImageView = (ImageView) findViewById(R.id.RedImageView);
        mBlueImageView = (ImageView) findViewById(R.id.BlueImageView);

        // seekBar
        SeekBar mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mVioletImageView.setBackgroundColor(Color.rgb(138, 48 + Long.valueOf((Math.round(progress * 2.07))).intValue(), 255));
                mPinkImageView.setBackgroundColor(Color.rgb(255, 38 + Long.valueOf((Math.round(progress * 2.17))).intValue(), 95));
                mRedImageView.setBackgroundColor(Color.rgb(255, Long.valueOf((Math.round(progress * 2.55))).intValue(), 0));
                mBlueImageView.setBackgroundColor(Color.rgb(0, Long.valueOf((Math.round(progress * 2.55))).intValue(), 255));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // Create a new AlertDialogFragment
            mDialog = MOMADialogFragment.newInstance();

            // Show AlertDialogFragment
            mDialog.show(getFragmentManager(), "MOMA");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void onDialogCancel() {
        Log.i(TAG, "DIALOG cancel");
    }

    void goToMOMA() {
        Log.i(TAG, "Dialog GO TO MOMA");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org/visit/calendar/exhibitions/1469"));
        startActivity(intent);
    }


    // Class that creates the AlertDialog
    public static class MOMADialogFragment extends DialogFragment {

        public static MOMADialogFragment newInstance() {
            return new MOMADialogFragment();
        }

        // Build AlertDialog using AlertDialog.Builder
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setMessage("Inspired by the works of artist such as Piet Mondrian and Ben Nicholson. \n\n Click below to learn more!")
                            // User cannot dismiss dialog by hitting back button
                    .setCancelable(false)
                    .setPositiveButton("Visit MOMA",
                            new DialogInterface.OnClickListener() {
                                public void onClick( final DialogInterface dialog, int id) {
                                    ((MainActivity)getActivity()).goToMOMA();
                                }
                            })
                    .setNegativeButton("Not Now",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ((MainActivity)getActivity()).onDialogCancel();
                        }
                    }).create();

        }
    }

}
