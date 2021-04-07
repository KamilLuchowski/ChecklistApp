package com.example.checklist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Class controls activity where user add or modify a task.
 */
public class AddEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        final Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final String type = bundle.getString(Strings.TYPE);
        final int position = bundle.getInt(Strings.POSITION);

        final EditText editTitle = findViewById(R.id.editTitle);
        final EditText editDesc = findViewById(R.id.editDesc);

        assert type != null;
        //if we modify a task, fill EditText
        if(type.equals(Strings.MODIFY)) {
            editTitle.setText(Tasklist.getListItem(position).getTitle(), EditText.BufferType.EDITABLE);
            editDesc.setText(Tasklist.getListItem(position).getDescription(), EditText.BufferType.EDITABLE);
        }

        FloatingActionButton confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editTitle.getText().toString();
                String desc = editDesc.getText().toString();

                if (!title.equals("")) {

                    Task task = new Task(title, desc);

                    if(type.equals(Strings.ADD)) {
                        Tasklist.addListItem(task, getApplicationContext());
                    }
                    else if(type.equals(Strings.MODIFY)){
                        Tasklist.setListItem(position, task, getApplicationContext());
                    }

                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), Strings.EMPTY_TITLE, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
