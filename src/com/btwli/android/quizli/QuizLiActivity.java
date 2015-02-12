package com.btwli.android.quizli;

import android.support.v4.app.Fragment;

public class QuizLiActivity extends SingleFragmentActivity {

	@Override
	protected Fragment creatFragment() {
		
		return new QuizLiFragment();
	}

}
