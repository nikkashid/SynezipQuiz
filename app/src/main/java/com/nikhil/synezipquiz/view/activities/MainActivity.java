package com.nikhil.synezipquiz.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
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

	private static final long TIMER_SPAN = 31000;

	private static final String QUIZ_COMPLETED = "Quiz has been completed.";

	MainActivityViewModel mainActivityViewModel;

	List<QuestionsTable> questionsTableList = new ArrayList<>();

	ActivityMainBinding activityMainBinding;

	private QuestionsTable currentQuestion;

	private int correctOption;

	private int questionCounter = 0;

	int score = 0;

	private CountDownTimer countDownTimer;

	private long timeLeftInMillis;

	private AnimationSet animation;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(activityMainBinding.getRoot());
		initViewModel();
		initAnimations();

		mainActivityViewModel.getAllQuestions().observe(this, new Observer<List<QuestionsTable>>()
		{
			@Override
			public void onChanged(List<QuestionsTable> questionsTables)
			{
				questionsTableList = questionsTables;
				if (questionsTables.size() > 0)
				{
					onOptionsClicked();
				}
			}
		});
	}

	private void initAnimations()
	{
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
		fadeIn.setDuration(2000);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
		fadeOut.setStartOffset(2000);
		fadeOut.setDuration(2000);

		animation = new AnimationSet(false); //change to false
		animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);
		activityMainBinding.rlInnerLayoutForRotation.setAnimation(animation);
	}

	private void onOptionsClicked()
	{
		setNextQuestion();

		activityMainBinding.llOptionOne.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (questionCounter < questionsTableList.size())
				{
					checkClickedEvent(1, activityMainBinding.btnOptionOne.getText().toString(),
							activityMainBinding.llOptionOne, activityMainBinding.btnOptionOne, true);
					if (questionCounter == 5)
						questionCounter++;
				}
				else
				{
					Toast.makeText(MainActivity.this, QUIZ_COMPLETED, Toast.LENGTH_SHORT).show();
				}
			}
		});

		activityMainBinding.llOptionTwo.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (questionCounter < questionsTableList.size())
				{
					checkClickedEvent(2, activityMainBinding.btnOptionTwo.getText().toString(),
							activityMainBinding.llOptionTwo, activityMainBinding.btnOptionTwo, true);
					if (questionCounter == 5)
						questionCounter++;
				}
				else
				{
					Toast.makeText(MainActivity.this, QUIZ_COMPLETED, Toast.LENGTH_SHORT).show();
				}
			}
		});

		activityMainBinding.llOptionThree.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (questionCounter <= questionsTableList.size())
				{
					checkClickedEvent(3, activityMainBinding.btnOptionThree.getText().toString(),
							activityMainBinding.llOptionThree, activityMainBinding.btnOptionThree, true);
					if (questionCounter == 5)
						questionCounter++;
				}
				else
				{
					Toast.makeText(MainActivity.this, QUIZ_COMPLETED, Toast.LENGTH_SHORT).show();
				}
			}
		});

		activityMainBinding.llOptionFour.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (questionCounter <= questionsTableList.size())
				{
					checkClickedEvent(4, activityMainBinding.btnOptionFour.getText().toString(),
							activityMainBinding.llOptionFour, activityMainBinding.btnOptionFour, true);
					if (questionCounter == 5)
						questionCounter++;
				}
				else
				{
					Toast.makeText(MainActivity.this, QUIZ_COMPLETED, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	private void checkClickedEvent(int position, String s, View viewToFlip, TextView textView, boolean fromOptionClick)
	{
		viewToFlip.setClickable(false);

		if (countDownTimer != null)
		{
			countDownTimer.cancel();
		}

		if (correctOption == position)
		{
			flipTextView(textView);
			flipLinearLayout(viewToFlip, true);
			if (fromOptionClick)
			{
				Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
				score++;
				activityMainBinding.tvScore.setText("Score : " + score * 10);
			}
			flipStarView();
		}
		else
		{
			Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
			flipLinearLayout(viewToFlip, false);
			flipTextView(textView);
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
		}, 2000);
	}

	private void forceCorrectAnswerFlip()
	{
		switch (correctOption)
		{
			case 1:
				checkClickedEvent(1, activityMainBinding.btnOptionOne.getText().toString(),
						activityMainBinding.llOptionOne, activityMainBinding.btnOptionOne, false);
				break;

			case 2:
				checkClickedEvent(2, activityMainBinding.btnOptionTwo.getText().toString(),
						activityMainBinding.llOptionTwo, activityMainBinding.btnOptionTwo, false);
				break;

			case 3:
				checkClickedEvent(3, activityMainBinding.btnOptionThree.getText().toString(),
						activityMainBinding.llOptionThree, activityMainBinding.btnOptionThree, false);
				break;

			case 4:
				checkClickedEvent(4, activityMainBinding.btnOptionFour.getText().toString(),
						activityMainBinding.llOptionFour, activityMainBinding.btnOptionFour, false);
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

	private void flipTextView(final View viewToFlip)
	{
		ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationX", 0f, 180f);
		flip.setDuration(0);
		flip.start();
	}

	private void flipStarView()
	{
		activityMainBinding.rlStartView.setVisibility(View.VISIBLE);
		initAnimations();
		ObjectAnimator flip = ObjectAnimator.ofFloat(activityMainBinding.rlInnerLayoutForRotation, "rotationY", 0f,
				360f);
		activityMainBinding.tvStarScore.setText("You Scored \n " + score * 10 + " \n Points");
		flip.setDuration(3000);
		flip.start();

		final Handler handler2 = new Handler();
		handler2.postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				activityMainBinding.rlStartView.setVisibility(View.GONE);
				setupNextQuestion();
			}
		}, 4000);
	}

	public void setNextQuestion()
	{
		if (questionCounter < questionsTableList.size())
		{
			currentQuestion = questionsTableList.get(questionCounter);

			activityMainBinding.progressBar.setProgress(0);

			activityMainBinding.tvScore.setText("Score : " + score * 10);

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
			timeLeftInMillis = TIMER_SPAN;
			startCountDown();
		}
		else
		{
			Toast.makeText(this, QUIZ_COMPLETED, Toast.LENGTH_SHORT).show();
		}
	}

	private void initViewModel()
	{
		mainActivityViewModel = ViewModelProviders.of(MainActivity.this).get(MainActivityViewModel.class);
	}

	private void startCountDown()
	{
		countDownTimer = new CountDownTimer(timeLeftInMillis, 1000)
		{
			@Override
			public void onTick(long millisUntilFinished)
			{
				timeLeftInMillis = millisUntilFinished;
				updateCountDownText();
			}

			@Override
			public void onFinish()
			{
				timeLeftInMillis = 0;
				updateCountDownText();
				forceCorrectAnswerFlip();
				Toast.makeText(MainActivity.this, "Times Up!!", Toast.LENGTH_SHORT).show();
				if (countDownTimer != null)
					countDownTimer.cancel();
			}
		}.start();
	}

	private void updateCountDownText()
	{
		int seconds = (int) (timeLeftInMillis / 1000) % 60;
		activityMainBinding.tvProgress.setText(seconds + "/30");
		if (seconds > 20)
		{
			activityMainBinding.progressBar
					.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.darkGreen)));
		}
		else if (seconds <= 20 && seconds > 15)
		{
			activityMainBinding.progressBar
					.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_light)));
		}
		else if (seconds <= 15 && seconds > 5)
		{
			activityMainBinding.progressBar
					.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_light)));
		}
		else
		{
			activityMainBinding.progressBar
					.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.red_dark)));
		}

		activityMainBinding.progressBar.setProgress(seconds);
	}
}