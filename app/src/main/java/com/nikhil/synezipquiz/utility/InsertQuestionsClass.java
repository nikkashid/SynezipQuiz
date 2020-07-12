package com.nikhil.synezipquiz.utility;

import com.nikhil.synezipquiz.application.AppApplication;
import com.nikhil.synezipquiz.entitiesForDB.QuestionsTable;
import com.nikhil.synezipquiz.repositories.QuestionsRepository;

public class InsertQuestionsClass
{
	QuestionsRepository questionsRepository;

	public InsertQuestionsClass(AppApplication appApplication)
	{
		questionsRepository = new QuestionsRepository(appApplication);
	}

	/*public void insertData()
	{
		QuestionsTable questionsTable1 = new QuestionsTable("Galileo was an Italian astronomer who developed?",
				"Telescope", "Airoplane", "Electricity", "Train", "Telescope");
		questionsRepository.insertQuestions(questionsTable1);

		QuestionsTable questionsTable2 = new QuestionsTable("Who is the father of Geometry ?", "Aristotle", "Euclid",
				"Pythagoras", "Kepler", "Euclid");
		questionsRepository.insertQuestions(questionsTable2);

		QuestionsTable questionsTable3 = new QuestionsTable("Who was known as Iron man of India ?",
				"Govind Ballabh Pant", "Jawaharlal Nehru", "Subhash Chandra Bose", "Sardar Vallabhbhai Patel",
				"Sardar Vallabhbhai Patel");
		questionsRepository.insertQuestions(questionsTable3);

		QuestionsTable questionsTable4 = new QuestionsTable("The first woman in space was ?", "Valentina Tereshkova",
				"Sally Ride", "Naidia Comenci", "Tamara Press", "Valentina Tereshkova");
		questionsRepository.insertQuestions(questionsTable4);

		QuestionsTable questionsTable5 = new QuestionsTable("Who is the Flying Sikh of India ?", "Mohinder Singh",
				"Joginder Singh", "Ajit Pal Singh", "Milkha singh", "Milkha singh");
		questionsRepository.insertQuestions(questionsTable5);

		QuestionsTable questionsTable6 = new QuestionsTable(
				"The Indian to beat the computers in mathematical wizardry is", "Ramanujam", "Rina Panigrahi",
				"Raja Ramanna", "Shakunthala Devi", "Shakunthala Devi");
		questionsRepository.insertQuestions(questionsTable6);

		QuestionsTable questionsTable7 = new QuestionsTable("Who is Larry Pressler ?", "Politician", "Painter", "Actor",
				"Tennis player", "Politician");
		questionsRepository.insertQuestions(questionsTable7);

		QuestionsTable questionsTable8 = new QuestionsTable(
				"Michael Jackson is a distinguished person in the field of ?", "Pop Music", "Jounalism", "Sports",
				"Acting", "Pop Music");
		questionsRepository.insertQuestions(questionsTable8);

		QuestionsTable questionsTable9 = new QuestionsTable("The first Indian to swim across English channel was ?",
				"V. Merchant", "P. K. Banerji", "Mihir Sen", "Arati Saha", "Mihir Sen");
		questionsRepository.insertQuestions(questionsTable9);

		QuestionsTable questionsTable10 = new QuestionsTable("Who was the first Indian to make a movie?",
				"Dhundiraj Govind Phalke", " Asha Bhonsle", " Ardeshir Irani", "V. Shantaram",
				"Dhundiraj Govind Phalke");
		questionsRepository.insertQuestions(questionsTable10);
	}*/
}
