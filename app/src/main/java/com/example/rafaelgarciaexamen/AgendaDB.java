package com.example.rafaelgarciaexamen;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Agenda.class}, version = 1, exportSchema = false)
public abstract class AgendaDB extends RoomDatabase {

    public abstract AgendaDao getAgendaDao();

    private volatile static com.example.rafaelgarciaexamen.AgendaDB INSTANCE;

    static com.example.rafaelgarciaexamen.AgendaDB getDb(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    com.example.rafaelgarciaexamen.AgendaDB.class, "agendadb").build();
        }
        return INSTANCE;
    }

}
