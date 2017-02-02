package com.faustosouza.networkconnection.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.faustosouza.networkconnection.R;
import com.faustosouza.networkconnection.manager.NetworkManager;

public class NetworkConnectionActivity extends AppCompatActivity implements IActivity {

    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;

    private TextView mTxtNetwork;
    private TextView mTxtNetworkType;

    private boolean isNetworkAvailable;
    private String networkType;

    private NetworkManager mNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_connection);

        initialize();
        loadData();
        loadView();
        events();

    }


    @Override
    public void initialize() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        mTxtNetwork = (TextView) findViewById(R.id.txtNetwork);
        mTxtNetworkType = (TextView) findViewById(R.id.txtNetworkType);

        mNetworkManager = new NetworkManager();
    }

    @Override
    public void loadData() {
        isNetworkAvailable = mNetworkManager.isConnectionAvailable(NetworkConnectionActivity.this);
        networkType = mNetworkManager.getNetworkType(NetworkConnectionActivity.this);
    }

    @Override
    public void loadView() {
        mTxtNetwork.setText(isNetworkAvailable ? getString(R.string.network_true) : getString(R.string.network_false));
        mTxtNetworkType.setText(networkType);
    }

    @Override
    public void events() {

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendEmailHelp();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_network_connection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /* refresh status network*/
        if (id == R.id.action_refresh) {

            loadData();
            loadView();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendEmailHelp() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:fausto.cps@hotmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "[NETWORK - GITHUB] Help");
        intent.putExtra(Intent.EXTRA_TEXT, "Help Network Connection!");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(NetworkConnectionActivity.this, getString(R.string.intent_email_failure), Toast.LENGTH_SHORT).show();

        }
    }
}
