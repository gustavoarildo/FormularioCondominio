package com.example.formulariocondominio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = null;

    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        bt = (Button)findViewById(R.id.button_voltaActv1);




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



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Main2Activity.this, MainActivity.class);


                i.putExtra("LISTA", listResponsaveisFinanceiros);


                startActivity(i);

            }
        });



    }
}
