package com.example.atividadeavaliativa1.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.atividadeavaliativa1.data.ticket.Ticket;
import com.example.atividadeavaliativa1.data.ticket.TicketDAO;
import com.example.atividadeavaliativa1.user.UserDao;
import com.example.atividadeavaliativa1.user.UserEntity;

@Database(entities = {UserEntity.class, Evento.class, Ticket.class}, version = 4, exportSchema = false)
public abstract class GeneralDatabase extends RoomDatabase {
    private static final String dbName = "dmovel.db";
    private static GeneralDatabase generalDatabase;
    public static synchronized GeneralDatabase getInstance(Context context) {
        if (generalDatabase == null) {
            generalDatabase = Room.databaseBuilder(context, GeneralDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return generalDatabase;
    }
    public abstract UserDao userDao();

    public abstract EventoDAO eventoDAO();

    public abstract TicketDAO ticketDAO();
}