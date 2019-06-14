package com.example.threadbarzel.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.threadbarzel.BaseActivity;
import com.example.threadbarzel.MyRunnable;
import com.example.threadbarzel.MyThread;
import com.example.threadbarzel.R;
import com.example.threadbarzel.models.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Inject
    MyThread mThread;

    @BindView(R.id.main_fragment_user_name_tv)
    TextView mUserNameTv;
    @BindView(R.id.main_fragment_user_email_tv)
    TextView mEmailTv;
    @BindView(R.id.main_fragment_user_phone_tv)
    TextView mPhoneTv;
    @BindView(R.id.main_fragment_user_website_tv)
    TextView mWebsiteTv;
    @BindView(R.id.activity_main_user_id_et)
    EditText mUserIdEt;
    @BindView(R.id.activity_main_btn_load_user_id)
    Button mLoadUserIdBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mThread.start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mThread.stopThread();
    }

    @OnClick(R.id.activity_main_btn_load_user_id)
    void loadUserId() {

        int userId;
        String userIdStr = mUserIdEt.getText().toString().trim();

        //Input Stuff.
        try {
            userId = Integer.valueOf(userIdStr);
        } catch (NumberFormatException e) {
            showLongToast(R.string.activity_main_edit_text_input_error);
            return;
        }

        if (userId < 1 || userId > 10) {
            showLongToast(R.string.activity_main_edit_text_input_error);
            return;
        }

        //Send a new request to our thread.
        mThread.loadUserId(userId, new MyRunnable() {
            @Override
            public void run() {
                loadUser(getUser());
            }
        });

    }

    private void loadUser(User user) {
        mUserNameTv.setText(user.getName());
        mEmailTv.setText(user.getEmail());
        mPhoneTv.setText(user.getPhone());
        mWebsiteTv.setText(user.getWebSite());
    }

}
