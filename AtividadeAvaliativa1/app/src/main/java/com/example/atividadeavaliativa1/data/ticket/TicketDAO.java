package com.example.atividadeavaliativa1.data.ticket;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Flowable;

import java.util.List;


@Dao
public interface TicketDAO {

    @Insert
    public Completable inserirTicket(Ticket ticket);

    @Query("SELECT * FROM ingresso")
    public Flowable<List<Ticket>> getAll();

}
