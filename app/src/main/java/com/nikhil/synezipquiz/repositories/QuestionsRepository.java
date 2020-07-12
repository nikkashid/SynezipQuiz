package com.nikhil.synezipquiz.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.nikhil.synezipquiz.dao.QuestionsDao;
import com.nikhil.synezipquiz.database.AppDataBase;
import com.nikhil.synezipquiz.entitiesForDB.QuestionsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QuestionsRepository
{
	private QuestionsDao questionsDao;

	MutableLiveData<List<QuestionsTable>> listLiveData = new MutableLiveData<>();

	List<QuestionsTable> questionsTablesList = new ArrayList<>();

	public QuestionsRepository(Application application)
	{
		AppDataBase appDataBase = AppDataBase.getInstance(application);
		questionsDao = appDataBase.questionsDao();
	}

	public void insertData()
	{
		QuestionsTable questionsTable1 = new QuestionsTable(
				"Which of these seek badges  for \"Budging,\" \"Flying Fish\" and \"Weather\"", "Candy Crush Players",
				"MENSA members", "Girl Scouts", "Boy Scouts", 2);
		insertQuestions(questionsTable1);
		questionsTablesList.add(questionsTable1);

		QuestionsTable questionsTable2 = new QuestionsTable("Backyard kingdom of castle builders and sediment shoveler",
				"A treehouse", "The swimming pool", "The snow fort", "The sandbox", 4);
		insertQuestions(questionsTable2);
		questionsTablesList.add(questionsTable2);

		QuestionsTable questionsTable3 = new QuestionsTable("Mario's older brother", "King Koopa", "Pikachu", "Luigi",
				"Grover", 3);
		insertQuestions(questionsTable3);
		questionsTablesList.add(questionsTable3);

		QuestionsTable questionsTable4 = new QuestionsTable("Quadrants are numbered in this ball game", "Tic-tac-toe",
				"Basketball", "Four square", "Tetherball", 3);
		insertQuestions(questionsTable4);
		questionsTablesList.add(questionsTable4);

		QuestionsTable questionsTable5 = new QuestionsTable("Two player,puck pushing table-top sport", "Pinball",
				"Checkers", "Air hockey", "Donkey kong", 3);
		insertQuestions(questionsTable5);
		questionsTablesList.add(questionsTable5);

		QuestionsTable questionsTable6 = new QuestionsTable(
				"The Indian to beat the computers in mathematical wizardry is", "Ramanujam", "Rina Panigrahi",
				"Raja Ramanna", "Shakunthala Devi", 4);
		//insertQuestions(questionsTable6);
		//questionsTablesList.add(questionsTable6);

		QuestionsTable questionsTable7 = new QuestionsTable("Who is Larry Pressler ?", "Politician", "Painter", "Actor",
				"Tennis player", 1);
		//insertQuestions(questionsTable7);
		//questionsTablesList.add(questionsTable7);

		QuestionsTable questionsTable8 = new QuestionsTable(
				"Michael Jackson is a distinguished person in the field of ?", "Pop Music", "Jounalism", "Sports",
				"Acting", 1);
		//insertQuestions(questionsTable8);
		//questionsTablesList.add(questionsTable8);

		QuestionsTable questionsTable9 = new QuestionsTable("The first Indian to swim across English channel was ?",
				"V. Merchant", "P. K. Banerji", "Mihir Sen", "Arati Saha", 3);
		//insertQuestions(questionsTable9);
		//questionsTablesList.add(questionsTable9);

		QuestionsTable questionsTable10 = new QuestionsTable("Who was the first Indian to make a movie?",
				"Dhundiraj Govind Phalke", " Asha Bhonsle", " Ardeshir Irani", "V. Shantaram", 1);
		//insertQuestions(questionsTable10);
		//questionsTablesList.add(questionsTable10);

		listLiveData.setValue(questionsTablesList);
	}

	public void insertQuestions(QuestionsTable questionsTable)
	{
		AsyncTask.execute(() -> insertQuestions(questionsTable));
	}

	public void fetchQuestions()
	{
		questionsDao.fetchQuestion().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribe(new MaybeObserver<List<QuestionsTable>>()
				{
					@Override
					public void onSubscribe(Disposable d)
					{

					}

					@Override
					public void onSuccess(List<QuestionsTable> questionsTables)
					{
						listLiveData.setValue(questionsTables);

						if (questionsTables.size() == 0)
						{
							insertData();
						}
					}

					@Override
					public void onError(Throwable e)
					{

					}

					@Override
					public void onComplete()
					{

					}
				});
	}

	public MutableLiveData<List<QuestionsTable>> getAllQuestions()
	{
		return listLiveData;
	}
}
