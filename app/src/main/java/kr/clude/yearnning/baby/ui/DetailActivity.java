package kr.clude.yearnning.baby.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;

import kr.clude.yearnning.baby.R;
import kr.clude.yearnning.baby.model.PersonSitter;


public class DetailActivity extends ActionBarActivity {

    /**
     * @param activity
     */
    public static void startActivity(Activity activity, int person_sitter_id) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra("arg_person_sitter_id", person_sitter_id);
        activity.startActivity(intent);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_activity);

        /**
         *
         */
        int person_sitter_id = getIntent().getExtras().getInt("arg_person_sitter_id");
        PersonSitter sitter = PersonSitter.newInstanceById(person_sitter_id);
        
        /**
         *
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setTitle(sitter.name);

        /**
         *
         */
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, DetailFragment.newInstance(person_sitter_id))
                    .commit();
        }
    }

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
        }

        return super.onOptionsItemSelected(item);
    }


}
