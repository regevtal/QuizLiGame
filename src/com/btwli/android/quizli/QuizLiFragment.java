package com.btwli.android.quizli;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewAnimator;

@SuppressLint("ClickableViewAccessibility")
public class QuizLiFragment extends Fragment {

	private static final String TAG = "QuizLiFragment";
	public static final String EXTRA_QUIZ_ID = "com.firstapp.android.picturealbum.quiz_id";
	private TextView mTextQuestionView;
	private ImageView mImageView;
	private ImageView mImageViewXwrongOrVrightAnswer;
	private ImageView mAnswerViewButtonOne;
	private ImageView mAnswerViewButtonTwo;
	private ImageView mAnswerViewButtonThree;
	private ImageButton mNextImageButton;
	private Drawable mTrueDrwPresident1;
	private Drawable mDrwPresident2;
	private Drawable mDrwPresident3;
	private Drawable mWorngXanswer;
	private Drawable mRightgXanswer;
	private ArrayList<QuizQuestion> mQuestionBank;
	private AnimationDrawable flagAnimation;
	private Animation slide_in_left1;
	private ViewAnimator viewPresidentAnimator;
	private AlertDialog alertWorngAnswer;
	private int mCurrentIndex = 0;;
	private boolean mCorrectAnswerIsTrue = false;
	private int mScore = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		getActivity().setTitle(R.string.app_name);

		mQuestionBank = QuizLiLab.get(getActivity()).getQuizQuestions();

	}

	@SuppressWarnings("deprecation")
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_quiz, parent, false);

		LinearLayout animImageFlag = (LinearLayout) v
				.findViewById(R.id.fragment_quiz_usa_flag_anim);
		// anim flag background
		animImageFlag.setBackgroundResource(R.drawable.usa_flag_anim);
		flagAnimation = (AnimationDrawable) animImageFlag.getBackground();
		flagAnimation.start();

		mImageViewXwrongOrVrightAnswer = (ImageView) v
				.findViewById(R.id.image_x_or_v_answer);
		mImageViewXwrongOrVrightAnswer.setImageDrawable(null);
		mImageViewXwrongOrVrightAnswer.setAlpha(100);

		mImageView = (ImageView) v.findViewById(R.id.image_president);
		mImageView.setImageDrawable(getResources().getDrawable(
				R.drawable.question_mark));

		mImageView.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (mCorrectAnswerIsTrue) {

					Intent i = new Intent(getActivity(),
							PresidentDescriptionActivity.class);
					i.putExtra(PresidentDescriptionFragment.EXTRA_QUESTION_ID,
							mQuestionBank.get(mCurrentIndex).getId());
					startActivity(i);
				}

				return true;
			}
		});

		mTextQuestionView = (TextView) v.findViewById(R.id.text_question_view);
		updateQuestion();

		// First answer possible
		mAnswerViewButtonOne = (ImageView) v
				.findViewById(R.id.image_list_layout_anim);
		updatePresident();
		mAnswerViewButtonOne.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				viewPresidentAnimator.showNext();
				mImageView.setImageDrawable(mTrueDrwPresident1);
				mRightgXanswer = getResources().getDrawable(
						R.drawable.v_right_answer);
				mImageViewXwrongOrVrightAnswer.setImageDrawable(mRightgXanswer);
				mCorrectAnswerIsTrue = true;
				mNextImageButton.setVisibility(View.VISIBLE);
				return true;
			}
		});

		// ------------------------------------------------------------------------------------------------------
		mAnswerViewButtonTwo = (ImageView) v.findViewById(R.id.image_answer2);
		mAnswerViewButtonThree = (ImageView) v.findViewById(R.id.image_answer3);
		updateWrongImage();

		mAnswerViewButtonTwo.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				viewPresidentAnimator.showNext();
				mImageView.setImageDrawable(mDrwPresident2);
				setWrongAnswer();
				getAlertWrongMessage();
				mScore = mScore + 1;
				mCorrectAnswerIsTrue = false;

				return true;
			}
		});

		mAnswerViewButtonThree.setOnTouchListener(new View.OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				viewPresidentAnimator.showNext();
				mImageView.setImageDrawable(mDrwPresident3);
				setWrongAnswer();
				getAlertWrongMessage();
				mCorrectAnswerIsTrue = false;
				return true;
			}
		});
		// -----------------------------------------------------------------------------------------------------------------

		mNextImageButton = (ImageButton) v.findViewById(R.id.next_imageButton);
		mNextImageButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (mCurrentIndex == mQuestionBank.size() - 1) {

					Intent i = new Intent(getActivity(),
							ListOfPresidentActivity.class);

					startActivity(i);

				} else {
					mCurrentIndex = mCurrentIndex + 1;
				}

				updateQuestion();
				updatePresident();
				updateWrongImage();
				clearForTheNextQuestion();

			}
		});

		viewPresidentAnimator = (ViewAnimator) v
				.findViewById(R.id.view_president_animator);
		setAnimation();

		return v;
	} // End of onCreateView

	private void updateWrongImage() {

		int two = mQuestionBank.get(mCurrentIndex)
				.getWrongDrawableAnswerSuccessorOne();
		int trhee = mQuestionBank.get(mCurrentIndex)
				.getWrongDrawableAnswerTwo();
		
		mDrwPresident2 = getResources().getDrawable(two);
		mDrwPresident3 = getResources().getDrawable(trhee);
		mAnswerViewButtonTwo.setImageDrawable(mDrwPresident2);
		mAnswerViewButtonThree.setImageDrawable(mDrwPresident3);

	}

	private void updateQuestion() {

		int question = mQuestionBank.get(mCurrentIndex).getQuestion();
		mTextQuestionView.setText(question);

	}

	private void updatePresident() {
		int pre = mQuestionBank.get(mCurrentIndex).getTrueDrawableAnswer();
		mTrueDrwPresident1 = getResources().getDrawable(pre);
		mAnswerViewButtonOne.setImageDrawable(mTrueDrwPresident1);

	}

	private void getAlertWrongMessage() {

		if (alertWorngAnswer != null && alertWorngAnswer.isShowing()) {
			return;
		} else {

			AlertDialog.Builder builder1 = new AlertDialog.Builder(
					getActivity());
			builder1.setMessage("Sorry Wrong Answer.");
			builder1.setCancelable(false);
			builder1.setPositiveButton("Back",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							mImageViewXwrongOrVrightAnswer
									.setImageDrawable(null);
							mImageView.setImageDrawable(getResources()
									.getDrawable(R.drawable.question_mark));
							dialog.cancel();

						}
					});

			alertWorngAnswer = builder1.create();
			alertWorngAnswer.show();
		}

	}

	private void clearForTheNextQuestion() {

		mNextImageButton.setVisibility(View.GONE);
		mImageView.setImageDrawable(getResources().getDrawable(
				R.drawable.question_mark));
		mImageViewXwrongOrVrightAnswer.setImageDrawable(null);
	};

	private void setWrongAnswer() {
		mWorngXanswer = getResources().getDrawable(R.drawable.wrong_x_answer);
		mImageViewXwrongOrVrightAnswer.setImageDrawable(mWorngXanswer);
	};

	private void setAnimation() {

		slide_in_left1 = AnimationUtils.loadAnimation(getActivity(),
				android.R.anim.slide_in_left);
		viewPresidentAnimator.setInAnimation(slide_in_left1);

	};

	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart() called");
	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onResume() {
		super.onResume();

		Log.d(TAG, "onResume() called");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}

}// end class
