package com.example.atividadeavaliativa1.data;
import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface EventoDAO {
    @Insert
    void inserirEvento(Evento evento);
}

