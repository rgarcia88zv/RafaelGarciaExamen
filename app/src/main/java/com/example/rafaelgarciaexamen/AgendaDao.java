package com.example.rafaelgarciaexamen;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AgendaDao {

    @Delete
    void delete(Agenda agenda);

    @Insert
    void insert(Agenda agenda);

    @Update
    void update(Agenda agenda);

    @Query("select * from contacto order by nombre")
    List<Agenda> getAll();

}
