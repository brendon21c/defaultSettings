package com.brendon.defaultsettings;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "com.brendon.";
    //ArrayList<String> todoListItems = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNewButton = (Button) findViewById(R.id.new_todo_item_button);
        final EditText newToDoEditText = (EditText) findViewById(R.id.new_todo_item_edittext);

        ListView todoListView = (ListView) findViewById(R.id.todo_listview);

        final ToDoListAdapter todoListAdapter = new ToDoListAdapter(this, R.layout.todo_list_item);

        todoListView.setAdapter(todoListAdapter);

        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newItemText = newToDoEditText.getText().toString();

                if (newItemText.length() == 0) {
                    Toast.makeText(MainActivity.this, "Enter a todo item", Toast.LENGTH_LONG).show();
                    return;
                }

                ToDoItem newItem = new ToDoItem(newItemText);

                todoListAdapter.add(new ToDoItem(newItemText));

                todoListAdapter.notifyDataSetChanged();

                newToDoEditText.getText().clear();
            }
        });

        todoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "Long click");

                final int indexPosition = position;    //Copy position clicked into final variable so it can be used inside the Dialog's event handler
                final ToDoItem item = todoListAdapter.getItem(position);

                AlertDialog areYouSureDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete this item?")
                        .setMessage(item.getText())
                        .setPositiveButton(R.string, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //remove item from ArrayList
                                todoListAdapter.remove(item);
                                //And notify Adapter of changes
                                todoListAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(R.string.no, null) /* no listener needed, if user presses this button the dialog will just close and nothing will happen */
                        .create();

                areYouSureDialog.show();

                return true;


            }
        });

    }
}
