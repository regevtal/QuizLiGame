package com.btwli.android.quizli;

import java.util.UUID;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class PresidentDescriptionFragment extends Fragment {

	private ImageView mPresidentImage;
	private TextView mPresidentTextView;
	private QuizQuestion mQuestion;
	private Animation slide_in_left1;
	private ViewAnimator viewPresidentAnimator;
	UUID questionId;

	public static final String EXTRA_QUESTION_ID = "com.firstapp.android.picturealbum.question_id";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.app_name);

		// mQuestionBank = QuizLiLab.get(getActivity()).getQuizQuestions();
		questionId = (UUID) getActivity().getIntent().getSerializableExtra(
				EXTRA_QUESTION_ID);
		mQuestion = QuizLiLab.get(getActivity()).getQuizQuestion(questionId);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_president_description,
				parent, false);
		mPresidentImage = (ImageView) v
				.findViewById(R.id.president_image_description);
		mPresidentImage.setImageDrawable(getResources().getDrawable(
				mQuestion.getTrueDrawableAnswer()));

		mPresidentTextView = (TextView) v
				.findViewById(R.id.president_text_view_description);
		mPresidentTextView.setText(mQuestion.getDescription());

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}

		viewPresidentAnimator = (ViewAnimator) v
				.findViewById(R.id.view_animator_president);
		slide_in_left1 = AnimationUtils.loadAnimation(getActivity(),
				android.R.anim.slide_in_left);
		viewPresidentAnimator.setInAnimation(slide_in_left1);

		viewPresidentAnimator.showNext();
		return v;
	}

	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_quiz_list, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			onBackPressed();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() == 0) {
			getActivity().finish();
		} else {
			getFragmentManager().popBackStack();
		}
	}

}// end class
