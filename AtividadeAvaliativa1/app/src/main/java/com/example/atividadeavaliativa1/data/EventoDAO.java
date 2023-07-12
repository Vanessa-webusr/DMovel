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
<<<<<<< HEAD
<<<<<<< HEAD
    public void inserirEvento(Evento evento);


=======
    public Completable inserirEvento(Evento evento);
=======

    void registerEvento(Evento evento);
    @Query("SELECT * FROM evento")
    List<Evento> loadAll();

    /*@Insert
    public Completable registerEvento(Evento evento);*/
>>>>>>> d5da3dfefee6818b276c1f270f0414b90adb68a4

    @Transaction
    @Query("SELECT * FROM evento WHERE id = :id ORDER BY id ASC")
    public Single<Evento> getEventoById(long id);
<<<<<<< HEAD
>>>>>>> evento
}

=======
}
>>>>>>> d5da3dfefee6818b276c1f270f0414b90adb68a4
