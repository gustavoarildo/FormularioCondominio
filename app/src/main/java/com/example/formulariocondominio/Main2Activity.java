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

    //ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = null;
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
        //ArrayList<String>  listData = new ArrayList<>();
        ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = new ArrayList<ResponsavelFinanceiro>();
        while(data.moveToNext()){
            //get the value from the database in colum 1
            //then add it to the ArrayList
            String[] colunas =  {data.getString(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4)};
            /*
            listData.add(data.getString(0));//os dados estao corretos
            listData.add(data.getString(1));
            listData.add(data.getString(2));
            listData.add(data.getString(3));
            listData.add(data.getString(4));
             //*/
            //listData.add(data.getExtras());
///*
            ResponsavelFinanceiro novo = new ResponsavelFinanceiro(
                    Integer.parseInt(colunas[0]),
                            colunas[1],
                            Integer.parseInt(colunas[2]),
                            Double.parseDouble(colunas[3]),
                            Double.parseDouble(colunas[4])
                    );
            listResponsaveisFinanceiros.add(novo);


 //*/
/*
            ResponsavelFinanceiro novo = new ResponsavelFinanceiro(
                    Integer.parseInt(data.getString(0),
                            data.getString(1),
                            Integer.parseInt(data.getString(2),
                            Double.parseDouble(data.getString(3)),
                            Double.parseDouble(data.getString(4))
                    );


 */

            exibeTextoNaTela(colunas[0]);
            exibeTextoNaTela(colunas[1]);
            exibeTextoNaTela(colunas[2]);
            exibeTextoNaTela(colunas[3]);
            exibeTextoNaTela(colunas[4]);

        }
        //create the List adapter and set the adapter
        //ListAdapter adapter = new ArrayAdapter<>(Main2Activity.this, android.R.layout.simple_list_item_1, listData);
        ArrayAdapter<ResponsavelFinanceiro> adapter = new ArrayAdapter<ResponsavelFinanceiro>(this, android.R.layout.simple_list_item_1, listResponsaveisFinanceiros);
        mListView.setAdapter(adapter);
    }


    public void exibeTextoNaTela(String meuTexto){
        Context context = getApplicationContext();
        CharSequence text = meuTexto;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
