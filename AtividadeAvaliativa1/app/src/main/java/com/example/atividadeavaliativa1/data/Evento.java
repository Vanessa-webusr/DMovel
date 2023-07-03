package com.example.atividadeavaliativa1.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "eventos")
public class Evento {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "nomeEvento")
    private String nomeEvento;

    @ColumnInfo(name = "dataEvento")
    private String dataEvento;

    @ColumnInfo(name = "localizacaoEvento")
    private String localizacaoEvento;

    @ColumnInfo(name = "descricaoEvento")
    private String descricaoEvento;

    // Construtor, getters e setters

    public Evento(String nomeEvento, String dataEvento, String localizacaoEvento, String descricaoEvento) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.localizacaoEvento = localizacaoEvento;
        this.descricaoEvento = descricaoEvento;
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
}
