package com.example.formulariocondominio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SecondFragment extends Fragment {



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //RECEBE ARRAYLIST
        try {
            Bundle data = getArguments();
            if(data != null) {
                ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = data.getParcelableArrayList("RESPONSAVEL");

                //ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = MainActivity.ExtraData.class.getClass().getMethods().getClass().getResource("listResponsaveisFinanceiros");

                // ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = getArguments().getParcelableArrayList ("RESPONSAVEL");//ok


                ListView listViewResponsaveisFinanceiros = (ListView) view.findViewById(R.id.ListView_ListarTodos);
                //ArrayList<ResponsavelFinanceiro> listResponsaveisFinanceiros = todosOsResponsaveisFinanceiros();
                ArrayAdapter<ResponsavelFinanceiro> adapter = new ArrayAdapter<ResponsavelFinanceiro>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_list_item_1, listResponsaveisFinanceiros);

                System.out.println(listResponsaveisFinanceiros.get(0).getNome());

                listViewResponsaveisFinanceiros.setAdapter(adapter);

            }



        }
        catch (Exception e)
        {
            //nao faz nada
            exibeTextoNaTela("second deu erro");
        }
        //RECEBE ARRAYLIST




        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }



/*
    private ArrayList<ResponsavelFinanceiro> todosOsResponsaveisFinanceiros() {

        return new ArrayList<>(Arrays.asList(

                new ResponsavelFinanceiro(
                        1,
                        "gu",
                        3713,
                        100,
                        1000)
        ));

    }

 */

    public void exibeTextoNaTela(String meuTexto){
        Context context = getActivity().getApplicationContext();
        CharSequence text = meuTexto;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }




}
