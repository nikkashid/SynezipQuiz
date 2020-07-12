package com.nikhil.synezipquiz.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nikhil.synezipquiz.dao.QuestionsDao;
import com.nikhil.synezipquiz.entitiesForDB.QuestionsTable;

@Database(entities = { QuestionsTable.class }, version = 1)
public abstract class AppDataBase extends RoomDatabase
{
	private static final String TAG = "AppDataBase";

	private static AppDataBase instance;

	public abstract QuestionsDao questionsDao();

	public static synchronized AppDataBase getInstance(Context context)
	{
		if (instance == null)
		{
			instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, TAG)
					.fallbackToDestructiveMigration().build();
		}

		return instance;
	}
}
