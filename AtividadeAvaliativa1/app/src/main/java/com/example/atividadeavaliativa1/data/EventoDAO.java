package com.example.atividadeavaliativa1.data;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventoDAO {
    @Insert
    void inserirEvento(Evento evento);

    @Query("SELECT * FROM evento")
    List<Evento> loadAll();
}

