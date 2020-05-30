package com.example.formulariocondominio;

import android.content.Context;
import android.content.Intent;

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
                if(edtNOME.length() != 0){
                    AddData(newEntry);
                    edtNOME.setText("");
                } else {
                    exibeTextoNaTela("You must put somenting in the text field");
                }





/*

                try {
                    ResponsavelFinanceiro novo = new ResponsavelFinanceiro(
                            Integer.parseInt(edtID.getText().toString()),
                            edtNOME.getText().toString(),
                            Integer.parseInt(edtTELEFONE.getText().toString()),
                            Double.parseDouble(edtMENSALIDADE.getText().toString()),
                            Double.parseDouble(edtDEBITOTOTAL.getText().toString())
                    );

                    boolean achei = false;
                    for (int i = 0; i < listResponsaveisFinanceiros.size(); i++) {
                        ResponsavelFinanceiro rf = (ResponsavelFinanceiro) listResponsaveisFinanceiros.get(i);
                        if (rf.getId() == (Integer.parseInt(edtID.getText().toString()))) {
                            achei = true;
                        }
                    }

                    if (achei) {
                        exibeTextoNaTela("CADASTRAMENTO MAL SUCEDIDO, ID JA EM USO NO MOMENTO");

                    }
                    else{
                        listResponsaveisFinanceiros.add(novo);
                        exibeTextoNaTela("CADASTRAMENTO BEM SUCEDIDO");
                        achei = false;


                    }
                }
                catch (Exception e){
                    exibeTextoNaTela("CADASTRAMENTO MAL SUCEDIDO PREENCHER TODOS CAMPOS");
                }



 */


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

            try {
                boolean achei = false;
                boolean idlistado = false;
                for (int i = 0; i < listResponsaveisFinanceiros.size(); i++) {
                    ResponsavelFinanceiro rf = (ResponsavelFinanceiro) listResponsaveisFinanceiros.get(i);
                    if (rf.getId() == (Integer.parseInt(edtID.getText().toString()))  ) {
                        achei = true;
                    }

                    if (achei) {
                        edtNOME.setText(rf.getNome());
                        edtTELEFONE.setText(String.valueOf(rf.getTelefone()));
                        edtMENSALIDADE.setText( String.valueOf(rf.getValorMensalidade()));
                        edtDEBITOTOTAL.setText( String.valueOf(rf.getDebitoTotal()));

                        achei = false;
                        idlistado = true;
                    }
                } // for
                if(listResponsaveisFinanceiros.size()>0 && idlistado == true) {
                    exibeTextoNaTela("CONSULTA POR CODIGO BEM SUCEDIDA, DADOS EXIBIDOS NOS CAMPOS");
                }
                else{
                    exibeTextoNaTela("LISTA NÃO POSSUI ESSE ID");
                }
            }
            catch (Exception e){
                exibeTextoNaTela("CONSULTA POR CODIGO MAL SUCEDIDA, PREENCHA O CAMPO ID COM O ID DO ITEM PROCURADO");
            }





            return true;
        }

        if (id == R.id.action_menu_cadastrar) {
            verificaAtualizacoesCampoTexto();
/*
            try {
                ResponsavelFinanceiro novo = new ResponsavelFinanceiro(
                        Integer.parseInt(edtID.getText().toString()),
                        edtNOME.getText().toString(),
                        Integer.parseInt(edtTELEFONE.getText().toString()),
                        Double.parseDouble(edtMENSALIDADE.getText().toString()),
                        Double.parseDouble(edtDEBITOTOTAL.getText().toString())
                );

                boolean achei = false;
                for (int i = 0; i < listResponsaveisFinanceiros.size(); i++) {
                    ResponsavelFinanceiro rf = (ResponsavelFinanceiro) listResponsaveisFinanceiros.get(i);
                    if (rf.getId() == (Integer.parseInt(edtID.getText().toString()))) {
                        achei = true;
                    }
                }

                if (achei) {
                    exibeTextoNaTela("CADASTRAMENTO MAL SUCEDIDO, ID JA EM USO NO MOMENTO");

                }
                else{
                    listResponsaveisFinanceiros.add(novo);
                    exibeTextoNaTela("CADASTRAMENTO BEM SUCEDIDO");
                    achei = false;


                }
            }
            catch (Exception e){
                exibeTextoNaTela("CADASTRAMENTO MAL SUCEDIDO PREENCHER TODOS CAMPOS");
            }



 */


            return true;
        }

        if (id == R.id.action_menu_listar_todos) {//ENVIA ARRAYLIST

            try {

                exibeTextoNaTela("setou a lista");

                //LISTAR TODOS VERSAO ACTIVIT ABAIXO
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                //i.putExtra("LISTA", listResponsaveisFinanceiros);
                startActivity(i);


            }
            catch (Exception e)
            {
                //nao faz nada
                exibeTextoNaTela("main deu erro");
            }


            return true;
        }//ENVIA ARRAYLIST

        if (id == R.id.action_menu_exclusao){
            verificaAtualizacoesCampoTexto();


            try {

                boolean achei = false;
                for (int i = 0; i < listResponsaveisFinanceiros.size(); i++) {
                    ResponsavelFinanceiro rf = (ResponsavelFinanceiro) listResponsaveisFinanceiros.get(i);
                    if (rf.getId() == (Integer.parseInt(edtID.getText().toString()))  ) {
                        achei = true;
                    }

                    if (achei) {
                        //rf
                        listResponsaveisFinanceiros.remove(i);
                        exibeTextoNaTela("EXCLUSÃO POR CODIGO BEM SUCEDIDA, DADOS DELETADOS");

                        achei = false;
                    }
                } // for

            }
            catch (Exception e){
                exibeTextoNaTela("EXCLUSÃO POR CODIGO MAL SUCEDIDA, PREENCHA O CAMPO ID COM O ID DO ITEM PROCURADO");
            }




            return true;
        }

        if (id == R.id.action_menu_alterar){
            verificaAtualizacoesCampoTexto();

            try {

                boolean achei = false;
                for (int i = 0; i < listResponsaveisFinanceiros.size(); i++) {
                    ResponsavelFinanceiro rf = (ResponsavelFinanceiro) listResponsaveisFinanceiros.get(i);
                    if (rf.getId() == (Integer.parseInt(edtID.getText().toString()))  ) {
                        achei = true;
                    }

                    if (achei) {
                        //rf
                        ResponsavelFinanceiro novo = new ResponsavelFinanceiro(
                                rf.getId(),
                                edtNOME.getText().toString(),
                                Integer.parseInt(edtTELEFONE.getText().toString()),
                                Double.parseDouble(edtMENSALIDADE.getText().toString()),
                                Double.parseDouble(edtDEBITOTOTAL.getText().toString())
                        );




                        listResponsaveisFinanceiros.set(i, novo);
                        exibeTextoNaTela("ALTERACAO POR CODIGO BEM SUCEDIDA, DADOS MODIFICADOS, ID NAO PODE SER MODIFICADO");

                        achei = false;
                    }

                } // for

            }
            catch (Exception e){
                exibeTextoNaTela("ALTERACAO POR CODIGO MAL SUCEDIDA, PREENCHA O CAMPO ID COM O ID DO ITEM PROCURADO");
            }







            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void AddData(String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if(insertData){
            exibeTextoNaTela("Data Sucessfully Inserted");
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
