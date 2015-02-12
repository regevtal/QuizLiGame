package com.btwli.android.quizli;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RuleQuizFragment extends Fragment {

	private TextView mTextView2;
	private TextView mTextView3;
	private TextView mTextView4;
	private TextView mTextView5;
	private TextView mTextView6;
	private TextView mTextView7;
	private TextView mTextView8;
	private Button mButtonStartGame;

	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_rule_quiz, parent, false);

		mTextView2 = (TextView) v.findViewById(R.id.textView2);
		mTextView3 = (TextView) v.findViewById(R.id.textView3);
		mTextView4 = (TextView) v.findViewById(R.id.textView4);
		mTextView5 = (TextView) v.findViewById(R.id.textView5);
		mTextView6 = (TextView) v.findViewById(R.id.textView6);
		mTextView7 = (TextView) v.findViewById(R.id.textView7);
		mTextView8 = (TextView) v.findViewById(R.id.textView8);

		mTextView2.setText(R.string.game_ruels);
		mTextView3.setText(R.string.game_ruels2);
		mTextView4.setText(R.string.game_ruels3);
		mTextView5.setText(R.string.game_ruels4);
		mTextView6.setText(R.string.game_ruels5);
		mTextView7.setText(R.string.game_ruels6);
		mTextView8.setText(R.string.game_ruels7);

		mButtonStartGame = (Button) v.findViewById(R.id.start_game_button);
		mButtonStartGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), QuizLiActivity.class);
				startActivity(i);

			}
		});
		return v;
	}
}// end class
