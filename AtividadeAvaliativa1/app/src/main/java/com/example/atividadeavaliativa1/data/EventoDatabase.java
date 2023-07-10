package com.example.atividadeavaliativa1.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Evento.class}, version = 2, exportSchema = false)
public abstract class EventoDatabase extends RoomDatabase {

    private static volatile EventoDatabase INSTANCE;

    public static synchronized EventoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EventoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EventoDatabase.class, "dmovel.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract EventoDAO eventoDAO();
}