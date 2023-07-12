package com.example.atividadeavaliativa1.data.ticket;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Dao
public interface TicketDAO {

    @Insert
    void inserirTicket(Ticket ticket);

    @Query("SELECT * FROM ingresso")
    List<Ticket> loadAll();


}
