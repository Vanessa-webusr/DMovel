package com.example.atividadeavaliativa1.data.ticket;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.Flowable;
import java.util.List;


@Dao
public interface TicketDAO {

    @Insert
    void inserirTicket(Ticket ticket);

    @Query("SELECT * FROM ingresso")
    Flowable<List<Ticket>> getAll();

    @Query("SELECT * FROM ingresso WHERE idUsuario=(:idUsuario)")
    Flowable<List<Ticket>> getAllByUser(Integer idUsuario);

}