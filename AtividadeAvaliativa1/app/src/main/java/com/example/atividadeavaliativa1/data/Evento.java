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

<<<<<<< HEAD
=======
    @ColumnInfo(name = "horaEvento")
    private String horaEvento;

>>>>>>> e15433e3d67414ca5f94686eea054e44dbbc5a14
    @ColumnInfo(name = "localizacaoEvento")
    private String localizacaoEvento;

    @ColumnInfo(name = "descricaoEvento")
    private String descricaoEvento;

    // Constructor, getters e setters

    public Evento(String nomeEvento, String dataEvento, String horaEvento, String localizacaoEvento, String descricaoEvento) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.horaEvento = horaEvento;
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
}
