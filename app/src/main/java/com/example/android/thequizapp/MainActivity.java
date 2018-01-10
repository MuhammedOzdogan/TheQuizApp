package com.example.android.thequizapp;

import android.content.res.Resources;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.android.thequizapp.Question.*;

public class MainActivity extends AppCompatActivity {
    private List<Question> questionsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private QuestionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This part about scroling to top from start of the app. It's automaticaly going on to ReclyerView I don't want that.
        final ScrollView sv = (ScrollView) findViewById(R.id.scrollView);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                sv.scrollTo(0, 0);
            }
        }, 250); // 250 ms delay

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new QuestionAdapter(questionsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareData();
    }

    /**
     * Preapering a data source for out Adapter
     */
    private void prepareData() {

        Resources res = getResources();
        questionsList.add(new Question<String>(getString(R.string.q1),
                getString(R.string.q1a1), getString(R.string.q1a2), getString(R.string.q1a3),
                RADIO,
                getString(R.string.correctAnswer1)
        ));

        questionsList.add(new Question<String[]>(getString(R.string.q2),
                getString(R.string.q2a1), getString(R.string.q2a2), getString(R.string.q2a3),
                CHECKBOX,
                res.getStringArray(R.array.correctAnswer2)));

        questionsList.add(new Question<String[]>(getString(R.string.q3),
                getString(R.string.q3a1), getString(R.string.q3a2), getString(R.string.q3a3),
                CHECKBOX,
                res.getStringArray(R.array.correctAnswer3)));

        questionsList.add(new Question<String[]>(getString(R.string.q4),
                getString(R.string.q4a1), getString(R.string.q4a2), getString(R.string.q4a3),
                CHECKBOX,
                res.getStringArray(R.array.correctAnswer4)));

        questionsList.add(new Question<String[]>(getString(R.string.q5),
                getString(R.string.q5a1), getString(R.string.q5a2), getString(R.string.q5a3),
                CHECKBOX,
                res.getStringArray(R.array.correctAnswer5)
        ));

        questionsList.add(new Question<String>(getString(R.string.q6),
                getString(R.string.q6a1), getString(R.string.q6a2), getString(R.string.q6a3),
                EDIT_TEXT,
                res.getString(R.string.correctAnswer6)));

        questionsList.add(new Question<String>(getString(R.string.q7),
                getString(R.string.q7a1), getString(R.string.q7a2), getString(R.string.q7a3),
                RADIO,
                res.getString(R.string.correctAnswer7)));

        questionsList.add(new Question<String[]>(getString(R.string.q8),
                getString(R.string.q8a1), getString(R.string.q8a2), getString(R.string.q8a3),
                CHECKBOX,
                res.getStringArray(R.array.correctAnswer8)));

        questionsList.add(new Question<String>(getString(R.string.q9),
                getString(R.string.q9a1), getString(R.string.q9a2), getString(R.string.q9a3),
                RADIO,
                res.getString(R.string.correctAnswer9)));

        questionsList.add(new Question<String>(getString(R.string.q10),
                getString(R.string.q10a1), getString(R.string.q10a2), getString(R.string.q10a3),
                EDIT_TEXT,
                res.getString(R.string.correctAnswer10)));


    }

    /**
     * This method using for calculatin correct answers.
     * @param view is a button which has a onclik event.
     */
    public void checkAnswers(View view) {
        //Datas of adapter
        List<Question> questions = mAdapter.getQuestionList();
        //Points of user
        int points = 0;
        //Looping every view.
        for(int i = 0; i < mAdapter.getItemCount(); i++) {
            //Get the parent closest view of question
            LinearLayout l = (LinearLayout) recyclerView.getLayoutManager().findViewByPosition(i);

            RadioGroup radioGroup = null;

            CheckBox checkBox1 = null;
            CheckBox checkBox2 = null;
            CheckBox checkBox3 = null;

            EditText editText =null;
            //Is this question have radio buttons.
            if (questions.get(i).getType() == RADIO) {
                radioGroup = l.findViewById(R.id.radioGroup);
                Integer checkedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = radioGroup.findViewById(checkedId);

                if(radioButton != null && radioButton.getText().equals(questions.get(i).getCorrectAnswer())) {
                 points++;//If checked radio button is correct we can increment points.
                }
                //Is this question have check box.
            } else if(questions.get(i).getType() == CHECKBOX) {
                String[] correctAnswers = (String[])questions.get(i).getCorrectAnswer();
                checkBox1 = l.findViewById(R.id.checkboxAnswer1);
                checkBox2 = l.findViewById(R.id.checkboxAnswer2);
                checkBox3 = l.findViewById(R.id.checkboxAnswer3);
                if(checkBox1.isChecked() && Arrays.asList(correctAnswers).contains(checkBox1.getText().toString())) {
                    points++;
                } else if(checkBox2.isChecked() && Arrays.asList(correctAnswers).contains(checkBox2.getText().toString())) {
                    points++;
                } else if(checkBox3.isChecked() && Arrays.asList(correctAnswers).contains(checkBox3.getText().toString())) {
                    points++;
                }

            } else if(questions.get(i).getType() == EDIT_TEXT) {
                editText = l.findViewById(R.id.textAnswer);
                if (editText.getText().toString().toUpperCase().trim().equals(questions.get(i).getCorrectAnswer())) {
                points++;
                }
            }




        }

        if(points == 10) {
            Toast.makeText(this,"Your points is : " + points + "\n Congratulations you are self confident person!",Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(this,"Your points is : " + points,Toast.LENGTH_LONG).show();

        }
    }

    }

