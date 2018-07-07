package id.aasumitro.examplesqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CartDBAdapter mDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartModel model = new CartModel();
                model.setSellerId("2");
                model.setSellerName("test new");
                model.setItemId("2");
                model.setItemName("test item new");
                model.setItemDesc("asd qwe zxc");
                model.setItemImage("http://localhst/qwe");
                model.setItemPrice("120000");
                mDBAdapter.add(model);
            }
        });

        mDBAdapter = new CartDBAdapter(getApplicationContext());
        mDBAdapter.openDB();
        initRecycler();
    }


    private void initRecycler() {
        RecyclerView mRecycler = findViewById(R.id.itemsRecyclerView);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        CartAdapter mAdapter = new CartAdapter(mDBAdapter.getAllRecord(), R.layout.item_list, getApplicationContext());
        mRecycler.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback=new CartSwipeHelper(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecycler);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("DATA", String.valueOf(mDBAdapter.getCartCount()));
        Log.d("PRICE", String.valueOf(mDBAdapter.getPriceCount()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDBAdapter.closeDB();
    }

}

