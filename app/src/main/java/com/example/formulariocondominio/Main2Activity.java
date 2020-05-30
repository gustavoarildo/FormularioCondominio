package com.example.formulariocondominio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    Button bt;

    private static final String TAG = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt = (Button)findViewById(R.id.button_voltaActv1);

        mListView = (ListView) findViewById(R.id.ListView_ListarTodosAtividade);
        mDatabaseHelper = new DatabaseHelper(Main2Activity.this);

        populateListView();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);

            }
        });



    }

    private void populateListView(){
        Log.d(TAG, "populateListView: Displayng data in the ListView");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = new ArrayList<ResponsavelFinanceiro>();
        while(data.moveToNext()){
            //get the value from the database in colum 1,2,3,4,5
            String[] colunas =  {data.getString(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4)};

            ResponsavelFinanceiro novo = new ResponsavelFinanceiro(
                    Integer.parseInt(colunas[0]),
                            colunas[1],
                            Integer.parseInt(colunas[2]),
                            Double.parseDouble(colunas[3]),
                            Double.parseDouble(colunas[4])
                    );
            listResponsaveisFinanceiros.add(novo);


        }
        //create the List adapter and set the adapter
        ArrayAdapter<ResponsavelFinanceiro> adapter = new ArrayAdapter<ResponsavelFinanceiro>(this, android.R.layout.simple_list_item_1, listResponsaveisFinanceiros);
        mListView.setAdapter(adapter);
    }

}
