package com.example.atividadeavaliativa1.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.atividadeavaliativa1.data.ticket.Ticket;
import com.example.atividadeavaliativa1.data.ticket.TicketDAO;
import com.example.atividadeavaliativa1.user.UserDao;
import com.example.atividadeavaliativa1.user.UserEntity;

@Database(entities = {UserEntity.class, Evento.class, Ticket.class}, version = 1)
public abstract class GeneralDatabase extends RoomDatabase {
    private static final String dbName = "dmovel.db";
    private static volatile GeneralDatabase generalDatabase;
    public abstract UserDao userDao();

    public abstract EventoDAO eventoDAO();

    public abstract TicketDAO ticketDAO();
    public static GeneralDatabase getInstance(Context context) {
        if (generalDatabase == null) {
            synchronized (GeneralDatabase.class){
                if(generalDatabase == null){
                    generalDatabase = Room.databaseBuilder(context, GeneralDatabase.class, dbName)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return generalDatabase;
    }

}
