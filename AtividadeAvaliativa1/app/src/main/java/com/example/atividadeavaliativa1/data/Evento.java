package com.example.atividadeavaliativa1.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "evento")
public class Evento {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nomeEvento")
    private String nomeEvento;

    @ColumnInfo(name = "dataEvento")
    private String dataEvento;


    @ColumnInfo(name = "horaEvento")
    private String horaEvento;


    @ColumnInfo(name = "localizacaoEvento")
    private String localizacaoEvento;

    @ColumnInfo(name = "descricaoEvento")
    private String descricaoEvento;


    // Constructor, getters e setters

    @ColumnInfo(name = "contatoEvento")
    private String contatoEvento;

    @ColumnInfo(name = "nomeContatoEvento")
    private String nomeContatoEvento;



    // Construtor, getters e setters


    public Evento(String nomeEvento, String dataEvento, String horaEvento, String localizacaoEvento, String descricaoEvento, String contatoEvento, String nomeContatoEvento) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.horaEvento = horaEvento;
        this.localizacaoEvento = localizacaoEvento;
        this.descricaoEvento = descricaoEvento;
        this.contatoEvento = contatoEvento;
        this.nomeContatoEvento = nomeContatoEvento;
    }

    public Evento(){
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getLocalizacaoEvento() {
        return localizacaoEvento;
    }

    public void setLocalizacaoEvento(String localizacaoEvento) {
        this.localizacaoEvento = localizacaoEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public String getContatoEvento() {
        return contatoEvento;
    }

    public void setContatoEvento(String contatoEvento) {
        this.contatoEvento = contatoEvento;
    }

    public String getNomeContatoEvento() {
        return nomeContatoEvento;
    }

    public void setNomeContatoEvento(String nomeContatoEvento) {
        this.nomeContatoEvento = nomeContatoEvento;
    }
}