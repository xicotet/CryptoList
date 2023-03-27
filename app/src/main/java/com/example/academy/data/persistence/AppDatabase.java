package com.example.academy.data.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.academy.data.model.CoinCard;

//Anotacion para que Room interprete que es una clase de control de la Base de Datos
@Database(entities = {CoinCard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    //Devuelve una conexion a la Base de Datos
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) { //Implementamos el patron SINGLETON
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class, "db"
                    ).build(); //Si no existe ninguna instancia de la conexion, creamos una nueva
        }
        return INSTANCE; //Devolvemos la instancia de la conexion
    }

    public abstract CoinDao getCoinDao();
}
