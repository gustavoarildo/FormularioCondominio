package com.example.formulariocondominio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Iterator;

public class FirstFragment extends Fragment {

    ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = new ArrayList<ResponsavelFinanceiro>();

    EditText edtID;
    EditText edtNOME;
    EditText edtTELEFONE;
    EditText edtMENSALIDADE;
    EditText edtDEBITOTOTAL;

    //ListView listViewResponsaveisFinanceiros;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtID = (EditText)view.findViewById(R.id.editText_Identificacao);
        edtNOME = (EditText)view.findViewById(R.id.editText_Nome);
        edtTELEFONE = (EditText)view.findViewById(R.id.editText_Telefone);
        edtMENSALIDADE = (EditText)view.findViewById(R.id.editText_Mensalidade);
        edtDEBITOTOTAL = (EditText)view.findViewById(R.id.editText_DebitoTotal);

        //listViewResponsaveisFinanceiros = (ListView) view.findViewById(R.id.ListView_ListarTodos);


        //ArrayAdapter<ResponsavelFinanceiro> adapter = new ArrayAdapter<ResponsavelFinanceiro>(this.getContext(), android.R.layout.simple_list_item_1,listResponsaveisFinanceiros);

       // listViewResponsaveisFinanceiros.setAdapter(adapter);


        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        view.findViewById(R.id.button_ConsultaPorCódigo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

            }
        });

        view.findViewById(R.id.button_Cadastramento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

            }
        });

        view.findViewById(R.id.button_ListarTodos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibeTextoNaTela("LISTAR TODOS");

            }
        });

        view.findViewById(R.id.button_Exclusão).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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










            }
        });

        view.findViewById(R.id.button_AlteraçãoDeDados).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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





            }
        });
    }

    public void exibeTextoNaTela(String meuTexto){
        Context context = getActivity().getApplicationContext();
        CharSequence text = meuTexto;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
