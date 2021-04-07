package com.example.checklist;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    TaskAdapter adapter;

    /**
     * Make the main activity ready
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Tasklist.read(getApplicationContext());
        FloatingActionButton addButton = findViewById(R.id.addTask);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddEditActivity.class);
                intent.putExtra(Strings.TYPE, Strings.ADD);
                startActivity(intent);

            }
        });

        PopupWindow popUp = new PopupWindow(this);

        ListView listView;
        adapter = new TaskAdapter(this, Tasklist.getList());

        listView = findViewById(R.id.list);

        listView.setAdapter(adapter);

    }

    /**
     * Refresh tasklist when user come back to main activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            Tasklist.removeCheckedItems(getApplicationContext());
            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Tasklist.write(getApplicationContext());
    }
}
