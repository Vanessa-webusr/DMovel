package com.example.atividadeavaliativa1.data;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface EventoDAO {
/*    @Insert
    void inserirEvento(Evento evento);*/

    @Query("SELECT * FROM evento")
    List<Evento> loadAll();

    @Query("SELECT * FROM evento ORDER BY id ASC")
    public Flowable<List<Evento>> getAll();
    @Insert
    public Completable inserirEvento(Evento evento);

    @Transaction
    @Query("SELECT * FROM evento WHERE id = :id ORDER BY id ASC")
    public Single<Evento> getEventoById(long id);
}