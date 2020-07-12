package com.nikhil.synezipquiz.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nikhil.synezipquiz.R;
import com.nikhil.synezipquiz.databinding.ActivityMainBinding;
import com.nikhil.synezipquiz.entitiesForDB.QuestionsTable;
import com.nikhil.synezipquiz.viewModels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private static final String TAG = "MainActivity";

	MainActivityViewModel mainActivityViewModel;

	List<QuestionsTable> questionsTableList = new ArrayList<>();

	ActivityMainBinding activityMainBinding;

	private QuestionsTable currentQuestion;

	private int correctOption;

	private int questionCounter = 0;

	private int status;

	Handler handler = new Handler();

	private Runnable runnable;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(activityMainBinding.getRoot());
		initViewModel();

		mainActivityViewModel.getAllQuestions().observe(this, new Observer<List<QuestionsTable>>()
		{
			@Override
			public void onChanged(List<QuestionsTable> questionsTables)
			{
				questionsTableList = questionsTables;
				Log.d(TAG, "onChanged: questionsTableList size:" + questionsTableList.size());
				for (int i = 0; i < questionsTableList.size(); i++)
				{
					Log.d(TAG, "onChanged: " + questionsTableList.get(i).toString());
				}
				onOptionsClicked();
			}
		});
	}

	private void onOptionsClicked()
	{
		setNextQuestion();

		activityMainBinding.llOptionOne.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				checkClickedEvent(1, activityMainBinding.btnOptionOne.getText().toString(),
						activityMainBinding.llOptionOne, activityMainBinding.btnOptionOne);

				//handler.
			}
		});

		activityMainBinding.llOptionTwo.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				checkClickedEvent(2, activityMainBinding.btnOptionTwo.getText().toString(),
						activityMainBinding.llOptionTwo, activityMainBinding.btnOptionTwo);
			}
		});

		activityMainBinding.llOptionThree.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				checkClickedEvent(3, activityMainBinding.btnOptionThree.getText().toString(),
						activityMainBinding.llOptionThree, activityMainBinding.btnOptionThree);
			}
		});

		activityMainBinding.llOptionFour.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				checkClickedEvent(4, activityMainBinding.btnOptionFour.getText().toString(),
						activityMainBinding.llOptionFour, activityMainBinding.btnOptionFour);
			}
		});
	}

	private void checkClickedEvent(int position, String s, View viewToFlip, TextView textView)
	{
		viewToFlip.setClickable(false);

		if (correctOption == position)
		{
			Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
			flipView(textView, true);
			flipLinearLayout(viewToFlip, true);
			setupNextQuestion();
		}
		else
		{
			Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
			flipLinearLayout(viewToFlip, false);
			flipView(textView, false);
			forceCorrectAnswerFlip();
		}

		textView.setText("");
		final Handler handler2 = new Handler();
		handler2.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				textView.setText(s);
				viewToFlip.setClickable(true);
			}
		}, 900);
	}

	private void setupNextQuestion()
	{
		final Handler handler2 = new Handler();
		handler2.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				setNextQuestion();
			}
		}, 3000);
	}

	private void forceCorrectAnswerFlip()
	{
		switch (correctOption)
		{
			case 1:
				checkClickedEvent(1, activityMainBinding.btnOptionOne.getText().toString(),
						activityMainBinding.llOptionOne, activityMainBinding.btnOptionOne);
				break;

			case 2:
				checkClickedEvent(2, activityMainBinding.btnOptionTwo.getText().toString(),
						activityMainBinding.llOptionTwo, activityMainBinding.btnOptionTwo);
				break;

			case 3:
				checkClickedEvent(3, activityMainBinding.btnOptionThree.getText().toString(),
						activityMainBinding.llOptionThree, activityMainBinding.btnOptionThree);
				break;

			case 4:
				checkClickedEvent(4, activityMainBinding.btnOptionFour.getText().toString(),
						activityMainBinding.llOptionFour, activityMainBinding.btnOptionFour);
				break;
		}
	}

	private void flipLinearLayout(View viewToFlip, boolean isCorrect)
	{
		ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationX", 0f, 180f);
		flip.setDuration(1000);
		flip.start();

		if (isCorrect)
		{
			viewToFlip.setBackground(getResources().getDrawable(R.drawable.correct_option_border_bg));
		}
		else
		{
			viewToFlip.setBackground(getResources().getDrawable(R.drawable.wrong_option_border_bg));
		}
	}

	private void flipView(final View viewToFlip, boolean isCorrect)
	{
		ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationX", 0f, 180f);
		flip.setDuration(0);
		flip.start();
	}

	public void setNextQuestion()
	{
		if (questionCounter < questionsTableList.size())
		{
			currentQuestion = questionsTableList.get(questionCounter);

			activityMainBinding.progressBar.setProgress(0);

			activityMainBinding.llOptionOne
					.setBackground(getResources().getDrawable(R.drawable.default_option_border_bg));
			activityMainBinding.llOptionTwo
					.setBackground(getResources().getDrawable(R.drawable.default_option_border_bg));
			activityMainBinding.llOptionThree
					.setBackground(getResources().getDrawable(R.drawable.default_option_border_bg));
			activityMainBinding.llOptionFour
					.setBackground(getResources().getDrawable(R.drawable.default_option_border_bg));

			activityMainBinding.tvQuestion.setText(currentQuestion.getQuestion());
			activityMainBinding.btnOptionOne.setText(currentQuestion.getOption1());
			activityMainBinding.btnOptionTwo.setText(currentQuestion.getOption2());
			activityMainBinding.btnOptionThree.setText(currentQuestion.getOption3());
			activityMainBinding.btnOptionFour.setText(currentQuestion.getOption4());
			correctOption = currentQuestion.getAnswer();
			questionCounter++;
			showProgressDialog();
		}
		else
		{
			Toast.makeText(this, "Quiz Completed.", Toast.LENGTH_SHORT).show();
		}
	}

	private void initViewModel()
	{
		mainActivityViewModel = ViewModelProviders.of(MainActivity.this).get(MainActivityViewModel.class);
	}

	public void showProgressDialog()
	{
		status = 0;

		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (status < 20)
				{
					status += 1;

					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}

					runnable = new Runnable()
					{
						@Override
						public void run()
						{
							activityMainBinding.progressBar.setProgress(status);
							activityMainBinding.tvProgress.setText(status + "/20");

							if (status == 20)
							{
								forceCorrectAnswerFlip();
							}
						}
					};

					handler.post(runnable);
				}
			}
		}).start();

	}
}