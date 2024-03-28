package site.ylan.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Demo控制器
 * @author ylan
 */

public class DemoActivity extends AppCompatActivity {

    private TextView mQuestionTextView;

    private Button mTureButton;
    private Button mFalseButton;

//    private Button mNextButton;
//    private Button mPreviousButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;

    private int mCurrentIndex = 0;
    private int question;
    private boolean trueQuestion;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_text_1,false),
            new TrueFalse(R.string.question_text_2,false),
            new TrueFalse(R.string.question_text_3,false),
            new TrueFalse(R.string.question_text_4,true),
            new TrueFalse(R.string.question_text_5,false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mQuestionTextView = findViewById(R.id.question_text_view);
        updateQuestion();

        mTureButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPreviousButton = findViewById(R.id.previous_button);

        // False监听器
        mTureButton.setOnClickListener(v -> {
            checkAnswer(true);
        });

        // True监听器
        mFalseButton.setOnClickListener(v -> {
            checkAnswer(false);
        });

        // 下一题监听器
        mNextButton.setOnClickListener(v -> {
            mCurrentIndex = Math.abs((mCurrentIndex + 1)) % mQuestionBank.length;
            updateQuestion();
        });

        // 上一题监听器
        mPreviousButton.setOnClickListener(v -> {
            mCurrentIndex = Math.abs((mCurrentIndex - 1)) % mQuestionBank.length;
            updateQuestion();
        });

        // 题目监听器 点击切换到下一题
        mQuestionTextView.setOnClickListener(v -> {
            mCurrentIndex = Math.abs((mCurrentIndex + 1)) % mQuestionBank.length;
            updateQuestion();
        });
    }

    /**
     * 更新问题
     */
    private void updateQuestion() {
        question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    /**
     * 检测答案是否正确
     *
     * @param userPressedTrue
     */
    private void checkAnswer(boolean userPressedTrue) {
        trueQuestion = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if (userPressedTrue == trueQuestion){
            messageResId = R.string.correct_toast;
        }else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}