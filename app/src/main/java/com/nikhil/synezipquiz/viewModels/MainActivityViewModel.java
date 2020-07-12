package com.nikhil.synezipquiz.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nikhil.synezipquiz.entitiesForDB.QuestionsTable;
import com.nikhil.synezipquiz.repositories.QuestionsRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel
{
	QuestionsRepository questionsRepository;

	public MainActivityViewModel(@NonNull Application application)
	{
		super(application);
		questionsRepository = new QuestionsRepository(application);
		questionsRepository.fetchQuestions();
	}

	public void insertQuestions(QuestionsTable questionsTable)
	{
		questionsRepository.insertQuestions(questionsTable);
	}

	public LiveData<List<QuestionsTable>> getAllQuestions()
	{
		return questionsRepository.getAllQuestions();
	}
}
