package com.example.tori.matchinggame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class FillInBlank extends ActionBarActivity {

    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_in_blank);

        spinner = (Spinner) findViewById(R.id.vocabSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vocabArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fill_in_blank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        //so that app doesn't crash when "up" button is pressed
        if (item.getItemId() == android.R.id.home) {
            // Handle "up" button behavior here.
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //added this to prevent error from user pressing back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void beginVocab(View view) {
        String curr = spinner.getSelectedItem().toString();
        Intent intent = new Intent(this, FillLessonJap.class);
        intent.putExtra("lesson", curr);
        startActivity(intent);
    }
}
