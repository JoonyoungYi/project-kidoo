package kr.clude.yearnning.baby.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import kr.clude.yearnning.baby.R;

public class ContactActivity extends ActionBarActivity {

    /**
     * @param activity
     */
    public static void startActivity(Activity activity, String name) {
        Intent intent = new Intent(activity, ContactActivity.class);
        intent.putExtra("arg_name", name);
        activity.startActivity(intent);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        String name = getIntent().getExtras().getString("arg_name");

        /**
         *
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setTitle(name + "님과 연락하기");

        /**
         *
         */
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ContactFragment.newInstance(null))
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_pay) {
            MaterialDialog.Builder builder = new MaterialDialog.Builder(ContactActivity.this)
                    .title("결제하기")
                    .content("정말 결제하시겠습니까?")
                    .negativeText("취소")
                    .positiveText("확인")
                    .autoDismiss(true);
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }


}
