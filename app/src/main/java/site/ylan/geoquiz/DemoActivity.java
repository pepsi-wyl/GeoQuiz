package site.ylan.geoquiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Demo控制器
 *
 * @author ylan
 */

public class DemoActivity extends AppCompatActivity {

    // TAG 常量
    private static final String TAG = "DemoActivity";

    // KEY_INDEX 常量
    private static final String KEY_INDEX = "index";

    private TextView mQuestionTextView;

    private Button mTureButton;
    private Button mFalseButton;

//    private Button mNextButton;
//    private Button mPreviousButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private Button mCheatButton;

    private int mCurrentIndex = 0;
    private int question;
    private boolean trueQuestion;

    private boolean mIsCheater;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_text_1, false),
            new TrueFalse(R.string.question_text_2, false),
            new TrueFalse(R.string.question_text_3, false),
            new TrueFalse(R.string.question_text_4, true),
            new TrueFalse(R.string.question_text_5, false),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        // onCreate方法
        Log.d(TAG, "onCreate(Bundle) called");

        mQuestionTextView = findViewById(R.id.question_text_view);
        // 初始化Activity1
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        updateQuestion();

        mTureButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mPreviousButton = findViewById(R.id.previous_button);
        mCheatButton = findViewById(R.id.cheat_button);

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
            mIsCheater = false;
            updateQuestion();
        });

        // Cheat监听器
        mCheatButton.setOnClickListener(v->{
            Intent intent = new Intent(DemoActivity.this, CheatActivity.class);
            trueQuestion = mQuestionBank[mCurrentIndex].isTrueQuestion();
            intent.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, trueQuestion);
//            startActivity(intent);
            startActivityForResult(intent, 0);
        });

        // 题目监听器 点击切换到下一题
        mQuestionTextView.setOnClickListener(v -> {
            mCurrentIndex = Math.abs((mCurrentIndex + 1)) % mQuestionBank.length;
            mIsCheater = false;
            updateQuestion();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // onStart方法
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // onResume方法
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        // onPause方法
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        // onStop方法
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // onDestroy方法
        Log.d(TAG, "onDestroy() called");
    }


    // Activity被系统回收时候保存数据
    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        saveInstanceState.putInt(KEY_INDEX, mCurrentIndex);
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
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        }else {
            if (userPressedTrue == trueQuestion) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

}