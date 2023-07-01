package com.example.atividadeavaliativa1.data;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Evento.class}, version = 1, exportSchema = false)
public abstract class EventoDatabase extends RoomDatabase {
    public abstract EventoDAO eventoDAO();
}
