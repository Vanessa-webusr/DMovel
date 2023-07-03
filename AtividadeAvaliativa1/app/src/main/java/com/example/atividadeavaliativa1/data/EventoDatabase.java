package com.example.atividadeavaliativa1.data;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Evento.class}, version = 1)
public abstract class EventoDatabase extends RoomDatabase {

    private static volatile EventoDatabase INSTANCE;

    public abstract EventoDAO eventoDAO();

    public static EventoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EventoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EventoDatabase.class, "evento.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
