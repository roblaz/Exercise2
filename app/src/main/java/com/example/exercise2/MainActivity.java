package com.example.exercise2;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add;
    Button clear;
    ArrayList<String> addArray = new ArrayList<String>();
    EditText txtFirstname;
    EditText txtLastname;
    ListView show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFirstname = (EditText)findViewById(R.id.firstname);
        txtLastname = (EditText)findViewById(R.id.lastname);
        show = (ListView)findViewById(R.id.ListView1);
        add = (Button) findViewById(R.id.add);
        clear = (Button) findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText)findViewById(R.id.firstname)).setText(" ");
                ((EditText)findViewById(R.id.lastname)).setText(" ");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getInputFirstName = txtFirstname.getText().toString();
                String getInputLastName = txtLastname.getText().toString();
                String FirstLastNames = getInputFirstName + " " + getInputLastName;

                if(addArray.contains(FirstLastNames)) {
                    Toast.makeText(getBaseContext(), "Item Already added to the array", Toast.LENGTH_LONG).show();
                }
                else if (FirstLastNames == null || FirstLastNames.trim().equals("")) {
                    Toast.makeText(getBaseContext(), "Input field is empty", Toast.LENGTH_LONG).show();
                }
                else {
                    addArray.add(FirstLastNames);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, addArray);
                    show.setAdapter(adapter);
                    ((EditText)findViewById(R.id.firstname)).setText(" ");
                    ((EditText)findViewById(R.id.lastname)).setText(" ");
                    Toast.makeText(getBaseContext(), "Added to list", Toast.LENGTH_LONG).show();
                }
            }
        });

        show.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("DELETE")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, addArray);
                                        show.setAdapter(adapter);
                                        for(int i=0; i< addArray.size();i++) {
                                            adapter.remove(addArray.get(i));
                                        }

                                        adapter.notifyDataSetChanged();

                                    }
                                })
                        .setCancelable(false)
                        .setNegativeButton("CANCEL",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alert = builder.create();
                alert.show();
                return false;
            }
        });
    }
}
