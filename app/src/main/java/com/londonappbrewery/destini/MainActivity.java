package com.londonappbrewery.destini;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView mStoryTextView;
    Button mAnswerTop;
    Button mAnswerBottom;
    // TODO: Declare as variaveis aqui:

    //indice corrente da historia
    private Story mStorySelected;

    Story mT1 = new Story(R.string.T1_Story);
    Answer mAnswerT1t = new Answer(R.string.T1_Ans1);
    Answer mAnswerT1b = new Answer(R.string.T1_Ans2);

    Story mT2 = new Story(R.string.T2_Story);
    Answer mAnswerT2t = new Answer(R.string.T2_Ans1);
    Answer mAnswerT2b = new Answer(R.string.T2_Ans2);

    Story mT3 = new Story(R.string.T3_Story);
    Answer mAnswerT3t = new Answer(R.string.T3_Ans1);
    Answer mAnswerT3b = new Answer(R.string.T3_Ans2);

    Story mT4 = new Story(R.string.T4_End);
    Answer mAnswerT4 = new Answer(R.string.T4_End);

    Story mT5 = new Story(R.string.T5_End);
    Answer mAnswerT5 = new Answer(R.string.T5_End);

    Story mT6 = new Story(R.string.T6_End);
    Answer mAnswerT6 = new Answer(R.string.T6_End);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO: Faça o link do layout com a activity
        mStoryTextView = findViewById(R.id.storyTextView);
        mAnswerTop = findViewById(R.id.buttonTop);
        mAnswerBottom = findViewById(R.id.buttonBottom);

        //TODO:faça o mapeamento da história
        mT1.setAnswerTop(mAnswerT1t);
        mAnswerT1t.setChildStory(mT3);
        mT1.setAnswerBottom(mAnswerT1b);
        mAnswerT1b.setChildStory(mT2);

        mT2.setAnswerTop(mAnswerT2t);
        mAnswerT2t.setChildStory(mT3);
        mT2.setAnswerBottom(mAnswerT2b);
        mAnswerT2b.setChildStory(mT5);

        mT3.setAnswerTop(mAnswerT3t);
        mAnswerT3t.setChildStory(mT6);
        mT3.setAnswerBottom(mAnswerT3b);
        mAnswerT3b.setChildStory(mT4);

        mStorySelected = mT1;

        mStoryTextView.setText(mStorySelected.getStoryID());
        mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
        mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());




        // TODO: Coloque o evento do click do botão, caso precise colocar a visibilidade no botão invisivel utilize a função
        // do botão setVisibility(View.GONE):

        mAnswerTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStorySelected = mStorySelected.getAnswerTop().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerTop() == null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }

            }
        });

        mAnswerBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStorySelected = mStorySelected.getAnswerBottom().getChildStory();
                mStoryTextView.setText(mStorySelected.getStoryID());
                if(mStorySelected.getAnswerTop() == null){
                    mAnswerTop.setVisibility(View.GONE);
                    mAnswerBottom.setVisibility(View.GONE);
                }else{
                    mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                    mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
                }

            }
        });


    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            mStorySelected = (Story) savedInstanceState.getSerializable("StoryKey");
            mStoryTextView.setText(mStorySelected.getStoryID());

            if(mStorySelected.getAnswerTop() == null){
                mAnswerTop.setVisibility(View.GONE);
                mAnswerBottom.setVisibility(View.GONE);
            }else{
                mAnswerTop.setText(mStorySelected.getAnswerTop().getAnswerID());
                mAnswerBottom.setText(mStorySelected.getAnswerBottom().getAnswerID());
            }
        }
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("StoryKey", mStorySelected);
    }

}
