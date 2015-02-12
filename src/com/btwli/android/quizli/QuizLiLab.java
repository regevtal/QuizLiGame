package com.btwli.android.quizli;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class QuizLiLab {

	private static QuizLiLab sQuizLiLab;
	@SuppressWarnings("unused")
	private Context mAppContext;
	private ArrayList<QuizQuestion> mListQuestionBank;

	private QuizLiLab(Context appContext) {
		mAppContext = appContext;
		mListQuestionBank = new ArrayList<QuizQuestion>();
		mListQuestionBank.add(new QuizQuestion(R.string.qeustion_president_1,
				R.drawable.george_washington, R.drawable.jhon_adams,
				R.drawable.franklin_roosevelt,
				R.string.george_washington_description,
				R.string.jhon_adams_description));

		mListQuestionBank.add(new QuizQuestion(R.string.qeustion_president_2,
				R.drawable.thomas_jeferson, R.drawable.james_madison,
				R.drawable.jhon_adams, R.string.thomas_jeferson_description,
				R.string.james_madison_description));

		mListQuestionBank.add(new QuizQuestion(R.string.qeustion_president_3,
				R.drawable.franklin_roosevelt, R.drawable.harry_truman,
				R.drawable.thomas_jeferson,
				R.string.franklin_roosevelt_description,
				R.string.harry_truman_description));

		mListQuestionBank.add(new QuizQuestion(R.string.qeustion_president_4,
				R.drawable.ronald_reagan, R.drawable.g_w_bush,
				R.drawable.george_washington,
				R.string.ronald_reagan_description,
				R.string.g_w_bush_description));

		mListQuestionBank.add(new QuizQuestion(R.string.qeustion_president_5,
				R.drawable.jhon_adams, R.drawable.thomas_jeferson,
				R.drawable.ronald_reagan, R.string.jhon_adams_description,
				R.string.thomas_jeferson_description));

	}

	public static QuizLiLab get(Context c) {
		if (sQuizLiLab == null) {
			sQuizLiLab = new QuizLiLab(c.getApplicationContext());
		}
		return sQuizLiLab;
	}

	public QuizQuestion getQuizQuestion(UUID id) {
		for (QuizQuestion q : mListQuestionBank) {
			if (q.getId().equals(id))
				return q;
		}
		return null;
	}

	public ArrayList<QuizQuestion> getQuizQuestions() {
		return mListQuestionBank;
	}

}