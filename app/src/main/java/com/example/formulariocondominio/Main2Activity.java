package com.example.formulariocondominio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

   // ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = null;
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



/*
        ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceirosIntent = null;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                listResponsaveisFinanceirosIntent= null;
            } else {
                listResponsaveisFinanceirosIntent = (ArrayList<ResponsavelFinanceiro>) extras.getSerializable("LISTA");
            }
        } else {
            listResponsaveisFinanceirosIntent = (ArrayList<ResponsavelFinanceiro>) savedInstanceState.getSerializable("LISTA");
        }
        listResponsaveisFinanceiros = listResponsaveisFinanceirosIntent;


        try {
            ListView listViewResponsaveisFinanceiros = (ListView) findViewById(R.id.ListView_ListarTodosAtividade);
            ArrayAdapter<ResponsavelFinanceiro> adapter = new ArrayAdapter<ResponsavelFinanceiro>(this, android.R.layout.simple_list_item_1, listResponsaveisFinanceiros);
            System.out.println(listResponsaveisFinanceiros.get(0).getNome());
            listViewResponsaveisFinanceiros.setAdapter(adapter);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


 */
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, MainActivity.class);
               // i.putExtra("LISTA", listResponsaveisFinanceiros);
                startActivity(i);

            }
        });



    }

    private void populateListView(){
        Log.d(TAG, "populateListView: Displayng data in the ListView");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String>  listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in colum 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the List adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(Main2Activity.this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);
    }
}
