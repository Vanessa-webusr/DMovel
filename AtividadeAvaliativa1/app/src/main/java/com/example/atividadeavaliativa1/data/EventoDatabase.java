package com.example.atividadeavaliativa1.data;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.atividadeavaliativa1.user.UserDao;
import com.example.atividadeavaliativa1.user.UserEntity;

@Database(entities = {Evento.class}, version = 1)
public abstract class EventoDatabase extends RoomDatabase {

    private static volatile EventoDatabase INSTANCE;

    public abstract EventoDAO eventoDAO();

    public static synchronized EventoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (EventoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EventoDatabase.class, "dmovel.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}