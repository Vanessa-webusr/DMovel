package com.example.atividadeavaliativa1.data.ticket;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Ticket.class}, version = 1)
public abstract class TicketDatabase extends RoomDatabase {

    private static volatile TicketDatabase INSTANCE;

    public abstract TicketDAO ticketDAO();

    public static TicketDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TicketDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TicketDatabase.class, "dmovel.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
