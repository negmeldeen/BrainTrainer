package com.example.muhammad.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playagainButton;
    RelativeLayout relativeLayout;
    TextView questiontextView;
    TextView resultTextView ;
    TextView pointTextView;
    TextView timeLeft;
    TextView correct;
    ArrayList<Integer> answers =new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    public void start (View view){
        startButton.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        playagainButton.setVisibility(View.INVISIBLE);
        correct.setVisibility(View.VISIBLE);


    }

    public void playAgain (View view){
        score=0;
        numberOfQuestions=0;
        timeLeft.setText("30s");
        correct.setVisibility(View.INVISIBLE);
        playagainButton.setVisibility(View.INVISIBLE);
        generateQuestions();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft.setText(String.valueOf(l /1000)+"s");

            }

            @Override
            public void onFinish() {
                timeLeft.setText("0s");
                resultTextView.setText("your score : "+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
                playagainButton.setVisibility(View.VISIBLE);

            }
        }.start();



    }
    public void generateQuestions(){
        Random rand =new Random();
        int a =rand.nextInt(21);
        int b =rand.nextInt(21);
        locationOfCorrectAnswer=rand.nextInt(4);
        int incorrectAnswer ;//if one of the incorrect answer = correctanswer
        answers.clear();
        for (int i=0 ; i < 4 ;i++){
            if (i == locationOfCorrectAnswer){
                answers.add( a + b);
            }
            else {
                incorrectAnswer=rand.nextInt(41);
                while (incorrectAnswer== a+b){
                    incorrectAnswer=rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
        resultTextView=(TextView) findViewById(R.id.correct);
        questiontextView.setText(Integer.toString(a)+"+"+ Integer.toString(b));

    }
    public void selectAnswer(View view){

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("correct");

        }
        else{
            resultTextView.setText("False!");
        }
        numberOfQuestions++;
        pointTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        generateQuestions();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.StartButton);
        relativeLayout=(RelativeLayout)findViewById(R.id.RelativeLayout);
        questiontextView=(TextView) findViewById(R.id.question);
        timeLeft= (TextView) findViewById(R.id.timeLeft);
        correct= (TextView) findViewById(R.id.correct);
        pointTextView=(TextView)findViewById(R.id.score);
         button0=(Button)findViewById(R.id.button0);
         button1=(Button)findViewById(R.id.button1);
         button2=(Button)findViewById(R.id.button2);
         button3=(Button)findViewById(R.id.button3);
         playagainButton=(Button)findViewById(R.id.playAgainButton);
       playAgain(findViewById(R.id.playAgainButton));




    }
}
