package site.ylan.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    // KEY_ANSWER_IS_TRUE 常量
    public static final String EXTRA_ANSWER_IS_TRUE = "site.ylan.geoquiz.answer_is_true";

    // EXTRA_ANSWER_SHOWN 常量
    public static final String EXTRA_ANSWER_SHOWN = "site.ylan.geoquiz.answer_shown";

    public boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        // 获取答案
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerTextView = findViewById(R.id.answerTextView);
        mShowAnswer = findViewById(R.id.showAnswerButton);


        // 默认不显示答案
        setAnswerShowResult(false);
        // 显示答案监视器
        mShowAnswer.setOnClickListener((v)->{
            if (mAnswerIsTrue){
                mAnswerTextView.setText(R.string.true_button);
            }else {
                mAnswerTextView.setText(R.string.false_button);
            }
            setAnswerShowResult(true);
        });
    }

    private void setAnswerShowResult(boolean isAnswerShown){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, intent);
    }

}