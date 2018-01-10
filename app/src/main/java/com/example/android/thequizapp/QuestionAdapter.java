package com.example.android.thequizapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.thequizapp.Question.*;

import java.util.List;

/**
 * Created by Mrvrctt on 10.01.2018.
 */

/**
 * This class for our RecyclerView it will use data an fill proper views.
 */
public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Data source for questions
    private List<Question> questionList;


    //This view type proper for questions which have onyl one correct answer.
    public static class OneRightAnswerQuesiton extends RecyclerView.ViewHolder {

        private TextView question;
        private TextView answer1;
        private TextView answer2;
        private TextView answer3;

        public OneRightAnswerQuesiton(View itemView) { //Context of layout
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.radioQuestion);
            answer1 = (TextView) itemView.findViewById(R.id.radioAnswer1);
            answer2 = (TextView) itemView.findViewById(R.id.radioAnswer2);
            answer3 = (TextView) itemView.findViewById(R.id.radioAnswer3);
        }
    }
    //This view type proper for questions which have multiple correct answers.
    public static class MultiAnswerQuestionViewHolder extends RecyclerView.ViewHolder{

        private TextView question;
        private CheckBox answer1;
        private CheckBox answer2;
        private CheckBox answer3;
        public MultiAnswerQuestionViewHolder(View itemView) { // Context of layout
            super(itemView);
            question = itemView.findViewById(R.id.checkboxQuestion);
            answer1 = itemView.findViewById(R.id.checkboxAnswer1);
            answer2 = itemView.findViewById(R.id.checkboxAnswer2);
            answer3 = itemView.findViewById(R.id.checkboxAnswer3);
        }
    }

    /**
     * This class can say 'There is no default constructor available in ...'
     * Becaus Compiler try to use non argument constroctor of  RecyclerView.ViewHolder
     * but there is no constructor with no argument, it contains one constructor with
     * one 'View itemView' parameter. So we must use this constructor with super keyword.
     */
    public static class InputAnswerQuestionViewHolder extends RecyclerView.ViewHolder {

        private TextView question;
        private EditText answer;

      public InputAnswerQuestionViewHolder(View itemView) {
            super(itemView);
            this.question = itemView.findViewById(R.id.textQuestion);
            this.answer = itemView.findViewById(R.id.textAnswer);
        }

    }

    /**
     * @param position of list element
     * @return is question has a TextEdit or has RadioGroup or has CheckBox
     */
    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return questionList.get(position).getType();
    }

    /**
     *
     * @param questionList consturctor for our Adapter.
     */
    public QuestionAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    //Selecting proper view for our data.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case RADIO :
                        View i1 = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.radio_question,parent,false);
                        return new OneRightAnswerQuesiton(i1);
            case CHECKBOX :
                        View i2 = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.checkbox_question,parent,false);
                        return new MultiAnswerQuestionViewHolder(i2);
            case EDIT_TEXT:
                        View i3 = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.text_entry_question,parent,false);
                        return new InputAnswerQuestionViewHolder(i3);
            default:
                return null;
        }
    }

    /**
     * This method filling context of our question.
     * @param holder is view for our quesiton.
     * @param position of question
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Question question = questionList.get(position);

        switch (holder.getItemViewType()) {
            case RADIO:
                OneRightAnswerQuesiton oneRightAnswerQuesiton = (OneRightAnswerQuesiton) holder;
                oneRightAnswerQuesiton.question.setText(question.getQuestion());
                oneRightAnswerQuesiton.answer1.setText(question.getAnswer1());
                oneRightAnswerQuesiton.answer2.setText(question.getAnswer2());
                oneRightAnswerQuesiton.answer3.setText(question.getAnswer3());
                break;
            case CHECKBOX:
                MultiAnswerQuestionViewHolder multiAnswerQuestionViewHolder = (MultiAnswerQuestionViewHolder)holder;
                multiAnswerQuestionViewHolder.question.setText(question.getQuestion());
                multiAnswerQuestionViewHolder.answer1.setText(question.getAnswer1());
                multiAnswerQuestionViewHolder.answer2.setText(question.getAnswer2());
                multiAnswerQuestionViewHolder.answer3.setText(question.getAnswer3());
                break;
            case EDIT_TEXT:
                InputAnswerQuestionViewHolder inputAnswerQuestionViewHolder = (InputAnswerQuestionViewHolder)holder;
                inputAnswerQuestionViewHolder.question.setText(question.getQuestion());
        }
    }

    /**
     *
     * @return size of our data source.
     */
    @Override
    public int getItemCount() {
        return questionList.size();
    }

    /**
     *
     * @return our data source.
     */
    public List<Question> getQuestionList() {
        return questionList;
    }

    /**
     *
     * @param questionList is our data source.
     */
    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
