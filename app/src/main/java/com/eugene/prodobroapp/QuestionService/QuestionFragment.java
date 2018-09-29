package com.eugene.prodobroapp.QuestionService;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eugene.prodobroapp.App;
import com.eugene.prodobroapp.R;
import com.eugene.prodobroapp.data.model.Answer;
import com.eugene.prodobroapp.data.model.Question;
import com.eugene.prodobroapp.data.source.local.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by eugene on 29.09.18.
 */

public class QuestionFragment extends Fragment {

    private static final AppDatabase appDatabase = App.getInstance().getAppDatabase();
    private static final int FIRST_QUESTION_ID = 1;

    public static QuestionFragment newInstance() {
        
        Bundle args = new Bundle();

        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Single.fromCallable(this::insertData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        View view = inflater.inflate(R.layout.question_layout, container, false);
        updateQuestionsAndAnswers(view, FIRST_QUESTION_ID);

        return view;
    }

    private int insertData() {
        try {
            List<Question> questionList = new ArrayList<>();
            questionList.add(new Question(1, "Как дела?", false));
            questionList.add(new Question(2, "Что тебя беспокоит?", false));
            questionList.add(new Question(3, "Какой тип тревоги ты испытываешь?", false));
            questionList.add(new Question(4, "Куча информации про панические атаки\nСпособы успокоиться во время панических атак:\nСпособ 1...\nСпособ 2...", true));
            questionList.add(new Question(5, "Какой тип насилия ты испытываешь?", false));
            questionList.add(new Question(6, "Кто является источником насилия?", false));
            questionList.add(new Question(7, "Есть ли близкий человек?", false));
            questionList.add(new Question(8, "Информация про то, куда нужна обращаться в таких ситуациях. Списки телефонов по городам. Ссылки на анонимные чаты.", true));
            questionList.add(new Question(1000, "Результат", true));

            List<Answer> answerList = new ArrayList<>();
            answerList.add(new Answer(1, "Плохо", 1, 2));
            answerList.add(new Answer(2, "Хорошо", 1, 1000));
            answerList.add(new Answer(3, "Одиночество", 2, 1000));
            answerList.add(new Answer(4, "Насилие", 2, 5));
            answerList.add(new Answer(5, "Тревога", 2, 3));
            answerList.add(new Answer(6, "Грусть", 2, 1000));
            answerList.add(new Answer(7, "Паника", 3, 4));
            answerList.add(new Answer(8, "Тревога за близких", 3, 1000));
            answerList.add(new Answer(9, "Фобия", 3, 1000));
            answerList.add(new Answer(10, "Физическое воздействие", 5, 1000));
            answerList.add(new Answer(11, "Эмоциональное давление", 5, 6));
            answerList.add(new Answer(12, "Родственники", 6, 1000));
            answerList.add(new Answer(13, "Одноклассники", 6, 7));
            answerList.add(new Answer(14, "Другие", 6, 1000));
            answerList.add(new Answer(15, "Да", 7, 1000));
            answerList.add(new Answer(16, "Нет", 7, 8));

            appDatabase.getQuestionDao().insertQuestions(questionList.toArray(new Question[questionList.size()]));
            appDatabase.getAnswerDao().insertAnswers(answerList.toArray(new Answer[answerList.size()]));
        } catch (Exception ignored) {
        }


        return 0;
    }

    private void updateQuestionsAndAnswers(View view, int questionId) {
        TextView textView = (TextView) view.findViewById(R.id.questionTextView);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.buttonsLinearLayout);
        Single.fromCallable(() -> getQuestionById(questionId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultQuestion -> {
                    textView.setText(resultQuestion.getText());
                    linearLayout.removeAllViews();
                    if (resultQuestion.isFinal()) {
                        Button button = new Button(getContext());
                        button.setText("В начало диалога");
                        button.setOnClickListener(l -> updateQuestionsAndAnswers(view, FIRST_QUESTION_ID));
                        linearLayout.addView(button);
                    }
                    Single.fromCallable(() -> getAnswersByPreviousQuestion(resultQuestion))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(answers -> {
                                for (Answer answer : answers) {
                                    Button button = new Button(getContext());
                                    button.setText(answer.getAnswerText());
                                    button.setOnClickListener(l -> {
                                        updateQuestionsAndAnswers(view, answer.getNextQuestionId());
                                    });
                                    linearLayout.addView(button);
                                }
                            });
                });
    }

    private Question getQuestionById(int id) {
        return appDatabase.getQuestionDao().getQuestionById(id);
    }

    private List<Question> getQuestions() {
        return appDatabase.getQuestionDao().getQuestions();
    }

    private List<Answer> getAnswersByPreviousQuestion(Question question) {
        return appDatabase.getAnswerDao().getAnswersByQuestionId(question.getId());
    }
}
