package com.example.atividadeavaliativa1.data.ticket;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingresso")
public class Ticket {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nomeEventoIngresso")
    private String nomeEventoIngresso;

    @ColumnInfo(name = "nomePessoa")
    private String nomePessoa;

    @ColumnInfo(name = "contatoEventoIngresso")
    private String contatoEventoIngresso;

    @ColumnInfo(name = "dataEventoIngresso")
    private String dataEventoIngresso;

    // Construtor, getters e setters

    public Ticket(String nomeEventoIngresso, String nomePessoa, String contatoEventoIngresso, String dataEventoIngresso) {
        this.nomeEventoIngresso = nomeEventoIngresso;
        this.nomePessoa = nomePessoa;
        this.contatoEventoIngresso = contatoEventoIngresso;
        this.dataEventoIngresso = dataEventoIngresso;
    }

    public Ticket(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEventoIngresso() {
        return nomeEventoIngresso;
    }

    public void setNomeEventoIngresso(String nomeEventoIngresso) {
        this.nomeEventoIngresso = nomeEventoIngresso;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getContatoEventoIngresso() {
        return contatoEventoIngresso;
    }

    public void setContatoEventoIngresso(String contatoEventoIngresso) {
        this.contatoEventoIngresso = contatoEventoIngresso;
    }

    public String getDataEventoIngresso() {return dataEventoIngresso;}

    public void setDataEventoIngresso(String dataEventoIngresso) { this.dataEventoIngresso = dataEventoIngresso;}

}