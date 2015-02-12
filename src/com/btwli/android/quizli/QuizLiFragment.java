package com.btwli.android.quizli;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.ViewAnimator;

@SuppressLint("ClickableViewAccessibility")
public class QuizLiFragment extends Fragment {

	private static final String TAG = "QuizLiFragment";
	public static final String EXTRA_QUIZ_ID = "com.firstapp.android.picturealbum.quiz_id";
	private TextView mTextQuestionView;
	private ImageView mImageQuestionView;
	private ImageView mAnswerViewOne;
	private ImageView mAnswerViewTwo;
	private ImageView mAnswerViewThree;
	private ImageButton mNextImageButton;
	private Drawable mTrueDrwPresident1;
	private Drawable mDrwPresident2;
	private Drawable mDrwPresident3;
	private ArrayList<QuizQuestion> mQuestionBank;
	private int mCurrentIndex = 0;;
	private boolean mCorrectAnswerIsTrue = false;
	private ViewAnimator viewAnimator1;
	private ViewAnimator viewAnimator2;
	private ViewAnimator viewAnimator3;
	Animation slide_in_left1;
	Animation slide_in_left2;
	Animation slide_in_left3;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		getActivity().setTitle(R.string.app_name);

		mQuestionBank = QuizLiLab.get(getActivity()).getQuizQuestions();

	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_quiz, parent, false);

		mImageQuestionView = (ImageView) v.findViewById(R.id.image_president);
		mImageQuestionView.setImageDrawable(getResources().getDrawable(
				R.drawable.question_mark));
		mImageQuestionView.setOnTouchListener(new View.OnTouchListener() {

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
		mAnswerViewOne = (ImageView) v.findViewById(R.id.image_answer_1);
		updatePresident();
		mAnswerViewOne.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				mImageQuestionView.setImageDrawable(mTrueDrwPresident1);
				mCorrectAnswerIsTrue = true;
				return true;
			}
		});

		// ------------------------------------------------------------------------------------------------------
		mAnswerViewTwo = (ImageView) v.findViewById(R.id.image_answer_2);
		mAnswerViewThree = (ImageView) v.findViewById(R.id.image_answer_3);
		updateWrongImage();

		mAnswerViewTwo.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				mImageQuestionView.setImageDrawable(getResources().getDrawable(
						R.drawable.wrong_answer));
				mCorrectAnswerIsTrue = false;

				return true;
			}
		});

		mAnswerViewThree.setOnTouchListener(new View.OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mImageQuestionView.setImageDrawable(getResources().getDrawable(
						R.drawable.wrong_answer));
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
				viewAnimator1.showNext();
				viewAnimator2.showNext();
				viewAnimator3.showNext();
				updateQuestion();
				updatePresident();
				updateWrongImage();

				mImageQuestionView.setImageDrawable(getResources().getDrawable(
						R.drawable.question_mark));

			}
		});

		viewAnimator1 = (ViewAnimator) v.findViewById(R.id.view_animator_1);

		slide_in_left1 = AnimationUtils.loadAnimation(getActivity(),
				android.R.anim.slide_in_left);
		viewAnimator1.setInAnimation(slide_in_left1);

		viewAnimator2 = (ViewAnimator) v.findViewById(R.id.view_animator_2);
		slide_in_left2 = AnimationUtils.loadAnimation(getActivity(),
				android.R.anim.slide_in_left);

		viewAnimator3 = (ViewAnimator) v.findViewById(R.id.view_animator_3);
		slide_in_left3 = AnimationUtils.loadAnimation(getActivity(),
				android.R.anim.slide_in_left);

		return v;
	}

	private void updateWrongImage() {

		int two = mQuestionBank.get(mCurrentIndex)
				.getWrongDrawableAnswerSuccessorOne();
		int trhee = mQuestionBank.get(mCurrentIndex)
				.getWrongDrawableAnswerTwo();
		mDrwPresident2 = getResources().getDrawable(two);
		mDrwPresident3 = getResources().getDrawable(trhee);
		mAnswerViewTwo.setImageDrawable(mDrwPresident2);
		mAnswerViewThree.setImageDrawable(mDrwPresident3);
	}

	private void updateQuestion() {

		int question = mQuestionBank.get(mCurrentIndex).getQuestion();
		mTextQuestionView.setText(question);

	}

	private void updatePresident() {
		int pre = mQuestionBank.get(mCurrentIndex).getTrueDrawableAnswer();
		mTrueDrwPresident1 = getResources().getDrawable(pre);
		mAnswerViewOne.setImageDrawable(mTrueDrwPresident1);

	}

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
