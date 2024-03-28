package site.ylan.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    // TAG 常量
    private static final String TAG = "CheatActivity";

    // KEY_ANSWER_IS_TRUE 常量
    public static final String EXTRA_ANSWER_IS_TRUE = "site.ylan.geoquiz.answer_is_true";

    // EXTRA_ANSWER_SHOWN 常量
    public static final String EXTRA_ANSWER_SHOWN = "site.ylan.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;

    private boolean isAnswerShow = false;

    private static final String KEY_IS_ANSWER_SHOW = "isAnswerShow";

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

        if (savedInstanceState != null) {
            isAnswerShow = savedInstanceState.getBoolean(KEY_IS_ANSWER_SHOW);
        }

        // 默认不显示答案
        setAnswerShowResult(isAnswerShow);
        // 显示答案监视器
        mShowAnswer.setOnClickListener((v)->{
            if (mAnswerIsTrue){
                mAnswerTextView.setText(R.string.true_button);
            }else {
                mAnswerTextView.setText(R.string.false_button);
            }
            isAnswerShow = true;
            setAnswerShowResult(isAnswerShow);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        saveInstanceState.putBoolean(KEY_IS_ANSWER_SHOW, isAnswerShow);
    }

    private void setAnswerShowResult(boolean flag){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShow);
        setResult(RESULT_OK, intent);
    }

}