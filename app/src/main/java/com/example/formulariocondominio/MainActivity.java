package com.example.formulariocondominio;

import android.content.Context;
import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  static  final  String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;


    ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = new ArrayList<ResponsavelFinanceiro>();


    EditText edtID;
    EditText edtNOME;
    EditText edtTELEFONE;
    EditText edtMENSALIDADE;
    EditText edtDEBITOTOTAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        verificaAtualizacoesCampoTexto();
        mDatabaseHelper = new DatabaseHelper(MainActivity.this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Voce precionou o botao de cadastramento", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                verificaAtualizacoesCampoTexto();


                String newEntry = edtNOME.getText().toString();
                String newEntry2 = edtTELEFONE.getText().toString();
                String newEntry3 = edtMENSALIDADE.getText().toString();
                String newEntry4 = edtDEBITOTOTAL.getText().toString();
                if(edtNOME.length() != 0 && edtTELEFONE.length() != 0 && edtMENSALIDADE.length() != 0 && edtDEBITOTOTAL.length() != 0){
                    AddData(newEntry, newEntry2, newEntry3, newEntry4);
                    edtNOME.setText("");
                    edtTELEFONE.setText("");
                    edtMENSALIDADE.setText("");
                    edtDEBITOTOTAL.setText("");
                } else {
                    exibeTextoNaTela("You must put somenting in the text field");
                }

            }
        });


        ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceirosIntent = new ArrayList<ResponsavelFinanceiro>();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                listResponsaveisFinanceirosIntent= new ArrayList<ResponsavelFinanceiro>();
            } else {
                listResponsaveisFinanceirosIntent = (ArrayList<ResponsavelFinanceiro>) extras.getSerializable("LISTA");
            }
        } else {
            listResponsaveisFinanceirosIntent = (ArrayList<ResponsavelFinanceiro>) savedInstanceState.getSerializable("LISTA");
        }
        listResponsaveisFinanceiros = listResponsaveisFinanceirosIntent;


        verificaAtualizacoesCampoTexto();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_consulta_codigo) {
            verificaAtualizacoesCampoTexto();



            boolean encontrado = false;
            Cursor data = mDatabaseHelper.getData();
            while(data.moveToNext()){
                String[] colunas =  {data.getString(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4)};
                if(colunas[0].equals(edtID.getText().toString())){
                    encontrado = true;
                    edtNOME.setText(colunas[1]);
                    edtTELEFONE.setText(colunas[2]);
                    edtMENSALIDADE.setText(colunas[3]);
                    edtDEBITOTOTAL.setText(colunas[4]);
                }
            }
            if (encontrado){
                exibeTextoNaTela("ID encontrado no banco");
            }else {
                exibeTextoNaTela("ID nao encontrado");
            }




            return true;
        }

        if (id == R.id.action_menu_cadastrar) {
            verificaAtualizacoesCampoTexto();

            String newEntry = edtNOME.getText().toString();
            String newEntry2 = edtTELEFONE.getText().toString();
            String newEntry3 = edtMENSALIDADE.getText().toString();
            String newEntry4 = edtDEBITOTOTAL.getText().toString();
            if(edtNOME.length() != 0 && edtTELEFONE.length() != 0 && edtMENSALIDADE.length() != 0 && edtDEBITOTOTAL.length() != 0){
                AddData(newEntry, newEntry2, newEntry3, newEntry4);
                edtNOME.setText("");
                edtTELEFONE.setText("");
                edtMENSALIDADE.setText("");
                edtDEBITOTOTAL.setText("");
            } else {
                exibeTextoNaTela("You must put somenting in the text field");
            }


            return true;
        }

        if (id == R.id.action_menu_listar_todos) {//ENVIA ARRAYLIST//Envia o utilizador para proxima tela

                exibeTextoNaTela("setou a lista");

                //LISTAR TODOS VERSAO ACTIVIT ABAIXO
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);

            return true;
        }//ENVIA ARRAYLIST//Envia o utilizador para proxima tela

        if (id == R.id.action_menu_exclusao){
            verificaAtualizacoesCampoTexto();

            boolean encontrado = false;
            Cursor data = mDatabaseHelper.getData();
            while(data.moveToNext()){
                String[] colunas =  {data.getString(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4)};
                if(colunas[0].equals(edtID.getText().toString())){
                    encontrado = true;
                    deleteData(colunas[0]);
                }
            }
            if (encontrado){
                exibeTextoNaTela("ID encontrado no banco");
            }else {
                exibeTextoNaTela("ID nao encontrado");
            }


            return true;
        }

        if (id == R.id.action_menu_alterar){
            verificaAtualizacoesCampoTexto();



            boolean encontrado = false;
            Cursor data = mDatabaseHelper.getData();
            while(data.moveToNext()){
                String[] colunas =  {data.getString(0),data.getString(1),data.getString(2),data.getString(3),data.getString(4)};
                if(colunas[0].equals(edtID.getText().toString())){
                    encontrado = true;
                    String newEntry = edtNOME.getText().toString();
                    String newEntry2 = edtTELEFONE.getText().toString();
                    String newEntry3 = edtMENSALIDADE.getText().toString();
                    String newEntry4 = edtDEBITOTOTAL.getText().toString();
                    if(edtNOME.length() != 0 && edtTELEFONE.length() != 0 && edtMENSALIDADE.length() != 0 && edtDEBITOTOTAL.length() != 0){
                        alteraData(colunas[0], newEntry, newEntry2, newEntry3, newEntry4);
                        edtNOME.setText("");
                        edtTELEFONE.setText("");
                        edtMENSALIDADE.setText("");
                        edtDEBITOTOTAL.setText("");
                    } else {
                        exibeTextoNaTela("You must put somenting in the text field");
                    }

                }
            }
            if (encontrado){
                exibeTextoNaTela("ID encontrado no banco");
            }else {
                exibeTextoNaTela("ID nao encontrado");
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddData(String newEntry, String newEntry2, String newEntry3, String newEntry4){
        boolean insertData = mDatabaseHelper.addData(newEntry, newEntry2, newEntry3, newEntry4);

        if(insertData){
            exibeTextoNaTela("Data Sucessfully Inserted");
        } else{
            exibeTextoNaTela("Somenting went wrong");
        }
    }

    public void deleteData(String newEntry){
        boolean excluiData = mDatabaseHelper.deleteData(newEntry);

        if(excluiData){
            exibeTextoNaTela("Data Sucessfully excluded");
        } else{
            exibeTextoNaTela("Somenting went wrong");
        }
    }

    public void alteraData(String id, String newEntry, String newEntry2, String newEntry3, String newEntry4){
        boolean modificaData = mDatabaseHelper.alteraData(id, newEntry, newEntry2, newEntry3, newEntry4);

        if(modificaData){
            exibeTextoNaTela("Data Sucessfully changed");
        } else{
            exibeTextoNaTela("Somenting went wrong");
        }
    }


    public void exibeTextoNaTela(String meuTexto){
        Context context = getApplicationContext();
        CharSequence text = meuTexto;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void verificaAtualizacoesCampoTexto(){
        edtID = (EditText)findViewById(R.id.editText_Identificacao);
        edtNOME = (EditText)findViewById(R.id.editText_Nome);
        edtTELEFONE = (EditText)findViewById(R.id.editText_Telefone);
        edtMENSALIDADE = (EditText)findViewById(R.id.editText_Mensalidade);
        edtDEBITOTOTAL = (EditText)findViewById(R.id.editText_DebitoTotal);
    }


}
