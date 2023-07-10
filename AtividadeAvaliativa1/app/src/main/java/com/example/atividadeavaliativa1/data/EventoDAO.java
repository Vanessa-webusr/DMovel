package com.example.atividadeavaliativa1.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface EventoDAO {

    @Query("SELECT * FROM evento ORDER BY id ASC")
    public Flowable<List<Evento>> getAll();
    @Insert
<<<<<<< HEAD
    public void inserirEvento(Evento evento);


=======
    public Completable inserirEvento(Evento evento);

    @Transaction
    @Query("SELECT * FROM evento WHERE id = :id ORDER BY id ASC")
    public Single<Evento> getEventoById(long id);
>>>>>>> evento
}

