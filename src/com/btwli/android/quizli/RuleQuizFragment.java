package com.btwli.android.quizli;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RuleQuizFragment extends Fragment {

	private TextView mTextView8;
	private ImageView mRulesView; 
	private Button mButtonStartGame;

	@SuppressWarnings("deprecation")
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_rule_quiz, parent, false);
		
		
		mRulesView = (ImageView) v.findViewById(R.id.rulesViewImage);
		mRulesView.setAlpha(150);
		mRulesView.setImageDrawable(getResources().getDrawable(R.drawable.quizli_ruels));
		
		mTextView8 = (TextView) v.findViewById(R.id.textView8);
		mTextView8.setText(R.string.game_ruels7);

		
		LinearLayout animImageFlag = (LinearLayout) v
				.findViewById(R.id.image_answer2);
		animImageFlag.setBackgroundResource(R.drawable.usa_flag_anim);
		AnimationDrawable flagAnimation = (AnimationDrawable) animImageFlag
				.getBackground();
		flagAnimation.start();

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
