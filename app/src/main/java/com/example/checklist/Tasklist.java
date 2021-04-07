package com.example.checklist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.checklist.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class keeps added tasks and provide adding, modyfying, deleting, saving and reading from SharedPreferences
 */
public class Tasklist {


    public static void setList(ArrayList<Task> list) {
        Tasklist.list = list;
    }

    private static ArrayList<Task> list = new ArrayList<Task>();


    public static Task getListItem(int i) {
        return list.get(i);
    }

    /**
     * Set task parameters
     * @param index position of task in list
     * @param editedTask reference to edited object
     * @param context
     */
    public static void setListItem(int index, Task editedTask, Context context) {
        list.set(index, editedTask);
        Tasklist.write(context);
    }

    /**
     * Add element to list
     * @param newTask Task object which will be added to list
     * @param context
     */
    public static void addListItem(Task newTask, Context context) {
        list.add(newTask);
        Tasklist.write(context);

    }

    /**
     * Delete element from list
     * @param position position of deleting element in list
     * @param context
     */
    public static void removeListItem(int position, Context context){
        list.remove(position);
        Tasklist.write(context);

    }

    public static ArrayList<Task> getList() {
        return list;
    }

    /**
     * Removes from list all task, which has isChecked==true
     * @param context
     */
    public static void removeCheckedItems(Context context){

        for (Iterator<Task> it = list.iterator(); it.hasNext();) {
            if (it.next().isChecked()) {
                it.remove();
            }
        }

        Tasklist.write(context);
    }

    /**
     * Save data from ArrayList list to SharedPreferences
     * @param context context of app
     */
    public static void write(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Strings.CHECKLIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString(Strings.TASKS, ObjectSerializer.serialize(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }

    /**
     * Read data from SharedPreferences to ArrayList list
     * @param context context of app
     */
    public static void read(Context context){


        if (null == list) {
            list = new ArrayList<>();
        }

        SharedPreferences prefs = context.getSharedPreferences(Strings.CHECKLIST, Context.MODE_PRIVATE);

        try {
            list = (ArrayList<Task>) ObjectSerializer.deserialize(prefs.getString(Strings.TASKS, ObjectSerializer.serialize(new ArrayList<Task>())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}