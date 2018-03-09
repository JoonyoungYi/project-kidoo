package kr.clude.yearnning.baby.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import kr.clude.yearnning.baby.R;
import kr.clude.yearnning.baby.model.PersonSitter;


public class WriteActivity extends ActionBarActivity {

    /**
     *
     */
    private static final String ARG_STORE_ID = "arg_store_id";
    private static final String ARG_STORE_CODE = "arg_store_code";
    private static final String ARG_STORE_TITLE = "arg_store_title";

    /**
     *
     */
    private int store_id = -1;

    /**
     *
     */
    private EditText mCommentEt;


    /**
     * @param activity
     */
    public static void startActivity(Activity activity, int sitter_id) {
        Intent intent = new Intent(activity, WriteActivity.class);
        intent.putExtra("arg_person_sitter_id", sitter_id);
        activity.startActivityForResult(intent, 0);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_activity);

        /**
         *
         */
        TextView mTitleTv = (TextView) findViewById(R.id.title_tv);
        ImageView mIconIv = (ImageView) findViewById(R.id.icon_iv);
        View mScrollView = findViewById(R.id.scrollView);
        mCommentEt = (EditText) findViewById(R.id.comment_et);

        /**
         *
         */
        int sitter_id = getIntent().getExtras().getInt("arg_person_sitter_id");
        PersonSitter sitter = PersonSitter.newInstanceById(sitter_id);

        /**
         *
         */
        mTitleTv.setText(sitter.name + "시터님을 어떻게 평가하시나요?");
        mIconIv.setImageResource(sitter.img_resource);

        /**
         *
         */
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_HOME_AS_UP);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.write, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;

        } else if (id == R.id.action_confirm) {
            requestCommentAddApi();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     */
    private void requestCommentAddApi() {
        String msg = mCommentEt.getText().toString();
        msg.trim();
        if (msg.length() < 10) {
            Toast.makeText(WriteActivity.this, "의견을 10자이상 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(WriteActivity.this, "의견 주셔서 감사합니다!", Toast.LENGTH_LONG).show();
            onBackPressed();
        }

    }


    /**
     *
     */
    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    public void onDestroy() {
        super.onDestroy();

    }


}
