package com.btwli.android.quizli;

import android.support.v4.app.Fragment;

public class ListOfPresidentActivity extends SingleFragmentActivity {

	@Override
	protected Fragment creatFragment() {

		return new ListOfPresidentFragment();
	}

}
