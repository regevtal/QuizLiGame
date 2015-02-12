package com.btwli.android.quizli;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListOfPresidentFragment extends ListFragment {
	private static final String TAG = "ListOfPresidentFragment";
	private ArrayList<QuizQuestion> mPresidentList;
	public static final String EXTRA_PRESIDENT_ID = "com.firstapp.android.picturealbum.president_id";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

		getActivity().setTitle(R.string.app_name);

		mPresidentList = QuizLiLab.get(getActivity()).getQuizQuestions();

		QuizAdapter adapter = new QuizAdapter(mPresidentList);
		setListAdapter(adapter);

	}// end onCreate

	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		@SuppressWarnings("unused")
		ListView listView = (ListView) v.findViewById(android.R.id.list);

		return v;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		QuizQuestion president = ((QuizAdapter) getListAdapter())
				.getItem(position);

		Intent i = new Intent(getActivity(), PresidentDescriptionActivity.class);
		i.putExtra(PresidentDescriptionFragment.EXTRA_QUESTION_ID,
				president.getId());
		startActivityForResult(i, 0);

	}

	private class QuizAdapter extends ArrayAdapter<QuizQuestion> {
		public QuizAdapter(ArrayList<QuizQuestion> presidentList) {
			super(getActivity(), android.R.layout.simple_list_item_1,
					presidentList);
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {

				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_president_fragment, null);
			}

			QuizQuestion quiz = getItem(position);

			ImageView presidentView = (ImageView) convertView
					.findViewById(R.id.president_image);
			presidentView.setImageDrawable(getResources().getDrawable(
					quiz.getTrueDrawableAnswer()));

			TextView presidentTextView = (TextView) convertView
					.findViewById(R.id.president_text);
			presidentTextView.setText(quiz.getDescription());

			ImageView presidentSuccessorView = (ImageView) convertView
					.findViewById(R.id.president_successor_image);
			presidentSuccessorView.setImageDrawable(getResources().getDrawable(
					quiz.getWrongDrawableAnswerSuccessorOne()));

			TextView presidentSuccessorTextView = (TextView) convertView
					.findViewById(R.id.president_successor_text);
			presidentSuccessorTextView.setText(quiz.getSuccessorDescription());

			return convertView;
		}
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	public void onResume() {
		super.onResume();

		Log.d(TAG, "onResume() called");
	}

}// End class

