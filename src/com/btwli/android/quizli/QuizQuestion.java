package com.btwli.android.quizli;

import java.util.UUID;

public class QuizQuestion {

	private int mQuestion;
	private UUID mId;
	private boolean mTrueQuestion;
	private int mTrueImageAnswer;
	private int mWrongImageAnswerSuccessorOne;
	private int mWrongImageAnswerTwo;
	private int mTrueAnswerDescription;

	public QuizQuestion(int question, int trueDrawableAnswer,
			int wrongImageAnswerOne, int wrongImageAnswerTwo, int description) {
		mQuestion = question;
		mTrueImageAnswer = trueDrawableAnswer;
		mWrongImageAnswerSuccessorOne = wrongImageAnswerOne;
		mWrongImageAnswerTwo = wrongImageAnswerTwo;
		mTrueAnswerDescription = description;
		mId = UUID.randomUUID();
	}

	public UUID getId() {
		return mId;
	}

	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

	public int getDescription() {
		return mTrueAnswerDescription;
	}

	public void setDescription(int description) {
		mTrueAnswerDescription = description;
	}

	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}

	public int getTrueDrawableAnswer() {
		return mTrueImageAnswer;
	}

	public void setTrueDrawableAnswer(int trueDrawableAnswer) {
		mTrueImageAnswer = trueDrawableAnswer;
	}

	public int getWrongDrawableAnswerSuccessorOne() {
		return mWrongImageAnswerSuccessorOne;
	}

	public void setWrongDrawableAnswerSuccessorOne(int wrongDrawableAnswerOne) {
		mWrongImageAnswerSuccessorOne = wrongDrawableAnswerOne;
	}

	public int getWrongDrawableAnswerTwo() {
		return mWrongImageAnswerTwo;
	}

	public void setWrongDrawableAnswerTwo(int wrongDrawableAnswerTwo) {
		mWrongImageAnswerTwo = wrongDrawableAnswerTwo;
	}

}
