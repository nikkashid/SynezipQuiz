package com.nikhil.synezipquiz.application;

import android.app.Application;

import com.nikhil.synezipquiz.repositories.QuestionsRepository;

public class AppApplication extends Application
{
	QuestionsRepository questionsRepository;

	@Override
	public void onCreate()
	{
		super.onCreate();
		//clearQuestionsTable();
	}

	/*private void clearQuestionsTable()
	{
		questionsRepository = new QuestionsRepository(this);
		questionsRepository.clearQuestionsTable();
	}*/
}
