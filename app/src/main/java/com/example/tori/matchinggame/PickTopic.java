package com.example.tori.matchinggame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PickTopic extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_topic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_topic, menu);
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
    //added this to prevent errors when user presses back button
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void startGame(View view) {
        String lang = null;

        lang = getIntent().getExtras().getString("gameType");

        //start fill-in-the-blank game
        if (lang.equals("fillInBlank")) {
            Intent intent = new Intent(this, FillInBlank.class);
            if (view.getId() == R.id.albanian) {
                intent.putExtra("topic", "albanian");
            } else if (view.getId() == R.id.french) {
                intent.putExtra("topic", "french");
            } else if (view.getId() == R.id.italian) {
                intent.putExtra("topic", "italian");
            } else if (view.getId() == R.id.japanese) {
                intent.putExtra("topic", "japanese");
            }
            startActivity(intent);
        } else if (lang.equals("matching")) { //start matching game
            Intent intent = new Intent(this, Matching.class);
            if (view.getId() == R.id.albanian) {
                intent.putExtra("topic", "albanian");
            } else if (view.getId() == R.id.french) {
                intent.putExtra("topic", "french");
            } else if (view.getId() == R.id.italian) {
                intent.putExtra("topic", "italian");
            } else if (view.getId() == R.id.japanese) {
                intent.putExtra("topic", "japanese");
            }
            startActivity(intent);
        }

    }
}
