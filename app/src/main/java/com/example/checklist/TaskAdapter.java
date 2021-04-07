package com.example.checklist;

import android.app.Activity;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Class supports layout of single task in ListView
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Activity context, ArrayList<Task> list) {

        super(context, 0, list);
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @Nullable ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Task task = getItem(position);
        assert task != null;

        TextView title = listItemView.findViewById(R.id.title);
        title.setText(task.getTitle());

        final CheckBox checkBox = listItemView.findViewById(R.id.checkBox);

        checkBox.setChecked(task.isChecked());


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tasklist.getListItem(position).setChecked(checkBox.isChecked());
                Tasklist.write(getContext());

            }

        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TaskDetails.class);
                intent.putExtra(Strings.POSITION, position);
                getContext().startActivity(intent);

            }

        });


        return listItemView;

    }

}
