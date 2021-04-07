package com.example.checklist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * TaskDetails class control activity with details of chosen task
 */
public class TaskDetails extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        position = bundle.getInt(Strings.POSITION);

        TextView title = findViewById(R.id.title);
        title.setText(Tasklist.getListItem(position).getTitle());

        TextView desc = findViewById(R.id.desc);
        desc.setText(Tasklist.getListItem(position).getDescription());

        final CheckBox checkBox = findViewById(R.id.details_checkbox);
        checkBox.setChecked(Tasklist.getListItem(position).isChecked());

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tasklist.getListItem(position).setChecked(checkBox.isChecked());
                Tasklist.write(getApplicationContext());
            }
        });

        FloatingActionButton deleteButton = findViewById(R.id.deleteButton);
        FloatingActionButton modifyButton = findViewById(R.id.modifyButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tasklist.removeListItem(position, getApplicationContext());
                finish();
            }
        });
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddEditActivity.class);
                intent.putExtra(Strings.TYPE, Strings.MODIFY);
                intent.putExtra(Strings.POSITION, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView title = findViewById(R.id.title);
        title.setText(Tasklist.getListItem(position).getTitle());

        TextView desc = findViewById(R.id.desc);
        desc.setText(Tasklist.getListItem(position).getDescription());

        final CheckBox checkBox = findViewById(R.id.details_checkbox);
        checkBox.setChecked(Tasklist.getListItem(position).isChecked());

    }
}
