package com.example.formulariocondominio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Parcelable;
import java.io.Serializable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = new ArrayList<ResponsavelFinanceiro>();

    //SecondFragment secondFragment = new SecondFragment();//?NANI?
    //FirstFragment firstFragment = new FirstFragment();//?
    //MainActivity mainActivity = new MainActivity();

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        verificaAtualizacoesCampoTexto();


        ResponsavelFinanceiro novo = new ResponsavelFinanceiro(
                1,
                "gu",
                3713,
                100,
                1000
        );
        listResponsaveisFinanceiros.add(novo);


        //FragmentTransaction fragmentTransaction;
/*
        Bundle bundle = new Bundle();//ok
        bundle.putParcelableArrayList("RESPONSAVEL", listResponsaveisFinanceiros);//ok
        SecondFragment secondFragment = new SecondFragment();//?NANI?
        secondFragment.setArguments(bundle);//?okokokokokok

 */

        //fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //getFragmentManager().beginTransaction()
                //.add(R.id.container, new PlaceholderFragment()).commit();
        ///fragmentTransaction = getSupportFragmentManager().findFragmentById(R.id.;
        //ExampleFragment fragment = (ExampleFragment) getSupportFragmentManager().findFragmentById(R.id.example_fragment);
        //fragmentTransaction.add(R.id.container, secondFragment);
        //fragmentTransaction.commit();

        //SharedPreferences pref = getApplicationContext().getSharedPreferences("DATA", MODE_PRIVATE);
        //SharedPreferences.Editor editor = pref.edit();
        //editor.putString("EDITEXT1", "string value");  // Salvando dados do seu editext
        //editor.commit(); // confirmar e salvar seus dados para a SharedPreferences

/*
        SecondFragment secondFragment = new SecondFragment();
        //FirstFragment firstFragment = new FirstFragment();
        if(listResponsaveisFinanceiros != null){



            Bundle bundle = new Bundle();



            //put your ArrayList data in bundle

            bundle.putSerializable("bundle_key", listResponsaveisFinanceiros);

            secondFragment.setArguments(bundle);
            //firstFragment.setArguments(bundle);

        }



        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.nav_host_fragment, secondFragment);
        //ft.replace(R.id.nav_host_fragment, firstFragment);

        ft.commit();

 */



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





                    //MainActivity mainActivity = new MainActivity();
                    //SecondFragment secondFragment = new SecondFragment();//?NANI?
                    //FragmentTransaction fragmentTransaction;
                   // Bundle bundle = new Bundle();//ok
                   // bundle.putParcelableArrayList("RESPONSAVEL", listResponsaveisFinanceiros);//ok
                    //secondFragment.setArguments(bundle);//?okokokokokok
                    //fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    //fragmentTransaction.add(R.id.FirstFragment, secondFragment);
                    //fragmentTransaction.commit();




                }
            }
            catch (Exception e){
                exibeTextoNaTela("CADASTRAMENTO MAL SUCEDIDO PREENCHER TODOS CAMPOS");
            }




            return true;
        }

        if (id == R.id.action_menu_listar_todos) {//ENVIA ARRAYLIST

            try {
                /*
                SecondFragment secondFragment = new SecondFragment();
                Bundle data = new Bundle();
                Intent intent = null;
                intent.putExtra("RESPONSAVEL", listResponsaveisFinanceiros);
                data = getIntent().getExtras();
                secondFragment.setArguments(data);

                 */

                //yourArrayList.add("test");
               // yourArrayList.add("test2")
               // Bundle bundle = new Bundle();//ok
               // bundle.putParcelableArrayList("RESPONSAVEL", listResponsaveisFinanceiros);//ok
                //SecondFragment secondFragment = new SecondFragment();//?
                //secondFragment.setArguments(bundle);//?okokokokokok
                //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();//????
                //fragmentTransaction.add(R.id.SecondFragment, secondFragment);//????
                //fragmentTransaction.commit();//ok?????????????????????


               // firstFragment.setArguments(bundle);//?okokokokokok


                exibeTextoNaTela("setou a lista");
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
