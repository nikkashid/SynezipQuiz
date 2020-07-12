package com.nikhil.synezipquiz.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nikhil.synezipquiz.entitiesForDB.QuestionsTable;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface QuestionsDao
{
	@Query("Delete from QuestionsTable")
	int clearQuestionsTable();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(QuestionsTable questionsTable);

	@Query("select * from QuestionsTable")
	Maybe<List<QuestionsTable>> fetchQuestion();
}
