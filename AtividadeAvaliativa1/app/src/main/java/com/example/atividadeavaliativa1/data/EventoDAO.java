package com.example.atividadeavaliativa1.data;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Transaction;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface EventoDAO {

    @Query("SELECT * FROM evento ORDER BY id ASC")
    public Flowable<List<Evento>> getAll();
    @Insert

    void registerEvento(Evento evento);
    @Query("SELECT * FROM evento")
    List<Evento> loadAll();

    /*@Insert
    public Completable registerEvento(Evento evento);*/

    @Transaction
    @Query("SELECT * FROM evento WHERE id = :id ORDER BY id ASC")
    public Single<Evento> getEventoById(long id);
}