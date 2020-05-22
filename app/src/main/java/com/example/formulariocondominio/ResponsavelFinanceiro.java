package com.example.formulariocondominio;

import android.os.Parcel;
import android.os.Parcelable;

public class ResponsavelFinanceiro implements Parcelable {
    private int id;
    private String nome;
    private int telefone;
    private double valorMensalidade;
    private double debitoTotal;

    public ResponsavelFinanceiro(int id, String nome, int telefone, double valorMensalidade, double debitoTotal){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.valorMensalidade = valorMensalidade;
        this.debitoTotal = debitoTotal;

    }

    protected ResponsavelFinanceiro(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        telefone = in.readInt();
        valorMensalidade = in.readDouble();
        debitoTotal = in.readDouble();
    }

    public static final Creator<ResponsavelFinanceiro> CREATOR = new Creator<ResponsavelFinanceiro>() {
        @Override
        public ResponsavelFinanceiro createFromParcel(Parcel in) {
            return new ResponsavelFinanceiro(in);
        }

        @Override
        public ResponsavelFinanceiro[] newArray(int size) {
            return new ResponsavelFinanceiro[size];
        }
    };

    @Override public String toString() {
        return "Id responsavel: " + id + " \nNome: " + nome + " \nTelefone: " + telefone  + " \nMensalidade: " + valorMensalidade  + " \nDebito total: " + debitoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public double getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(double valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public double getDebitoTotal() {
        return debitoTotal;
    }

    public void setDebitoTotal(double debitoTotal) {
        this.debitoTotal = debitoTotal;
    }










    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nome);
        dest.writeInt(telefone);
        dest.writeDouble(valorMensalidade);
        dest.writeDouble(debitoTotal);
    }
}
