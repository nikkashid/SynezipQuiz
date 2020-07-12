package com.nikhil.synezipquiz.entitiesForDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuestionsTable
{
	@PrimaryKey(autoGenerate = true)
	public int uid;

	@ColumnInfo(name = "question")
	String question;

	@ColumnInfo(name = "option1")
	String option1;

	@ColumnInfo(name = "option2")
	String option2;

	@ColumnInfo(name = "option3")
	String option3;

	@ColumnInfo(name = "option4")
	String option4;

	@ColumnInfo(name = "answer")
	int answer;

	public QuestionsTable(String question, String option1, String option2, String option3, String option4,
						  int answer)
	{
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
	}

	public String getQuestion()
	{
		return question;
	}

	public void setQuestion(String question)
	{
		this.question = question;
	}

	public String getOption1()
	{
		return option1;
	}

	public void setOption1(String option1)
	{
		this.option1 = option1;
	}

	public String getOption2()
	{
		return option2;
	}

	public void setOption2(String option2)
	{
		this.option2 = option2;
	}

	public String getOption3()
	{
		return option3;
	}

	public void setOption3(String option3)
	{
		this.option3 = option3;
	}

	public String getOption4()
	{
		return option4;
	}

	public void setOption4(String option4)
	{
		this.option4 = option4;
	}

	public int getAnswer()
	{
		return answer;
	}

	public void setAnswer(int answer)
	{
		this.answer = answer;
	}

	@Override
	public String toString()
	{
		return "QuestionsTable{" + "uid=" + uid + ", question='" + question + '\'' + ", option1='" + option1 + '\''
				+ ", option2='" + option2 + '\'' + ", option3='" + option3 + '\'' + ", option4='" + option4 + '\''
				+ ", answer='" + answer + '\'' + '}';
	}
}
