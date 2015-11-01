package com.example.tori.matchinggame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class MatchLessonJap extends ActionBarActivity {

    HashMap sports, fruits, curr;
    String randomKey, randomChoice;
    int value;
    List<String> keys, old, oldChoice;
    ArrayList<Button> buttons;
    Random random;
    Button one, two, three, four, randButt;
    ImageView image;
    TextView lessonText;
    int curr_score, total, counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_lesson_jap);

        //fill up sports hashmap
        sports = new HashMap<>();
        sports.put("basukettobouru", R.drawable.basketball);
        sports.put("gorufu", R.drawable.golf);
        sports.put("sakkaa", R.drawable.soccer);
        sports.put("oyogu", R.drawable.swimming);
        sports.put("tenisu", R.drawable.tennis);
        //fill up food hashmap
        fruits = new HashMap<>();
        fruits.put("ringo", R.drawable.apple);
        fruits.put("banana", R.drawable.banana);
        fruits.put("budou", R.drawable.grapes);
        fruits.put("mikan", R.drawable.orange);
        fruits.put("ichigo", R.drawable.strawberry);

        //see which vocab set to use!
        curr = new HashMap();
        String lessonType = getIntent().getExtras().getString("lesson");
        if (lessonType.equals("Sports")) {
            curr = sports;
        } else if (lessonType.equals("Fruits")) {
            curr = fruits;
        }
        //now that we have the right map, we can begin the game
        curr_score = 0;
        total = 0;
        lessonText = (TextView) findViewById(R.id.lessonText);
        lessonText.setText("Score: " + curr_score + "/" + total);

        //trying to resize
        image = (ImageView) findViewById(R.id.image);

        random = new Random();
        keys = new ArrayList<String>(curr.keySet()); //fix to curr
        randomKey = keys.get( random.nextInt(keys.size()) );
        value = (int) curr.get(randomKey); //fix to curr

        //old is used to keep track of what images we have used
        old = new ArrayList<>();
        oldChoice = new ArrayList<>();
        oldChoice.add(randomKey);

        //sets image
        image.setImageResource(value);
        counter = 1;

        //puts buttons in a list
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        buttons = new ArrayList<>();
        buttons.add(one);
        buttons.add(two);
        buttons.add(three);
        buttons.add(four);

        //puts answer on random button
        randButt = buttons.get(random.nextInt(4));
        randButt.setText(randomKey);
        //assigns rest of buttons
        for (int i = 0; i < buttons.size(); i++) {
            //while I've already used that choice
            while (oldChoice.contains(randomChoice)) {
                random = new Random();
                randomChoice = keys.get( random.nextInt(keys.size()) );
            }
            if ( !buttons.get(i).getText().equals(randomKey) ) {
                buttons.get(i).setText(randomChoice);
                oldChoice.add(randomChoice);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fill_lesson_jap, menu);
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

    public void submit(View view) {
        Button b = (Button) view;
        String selected = b.getText().toString();

        if (counter == curr.size()) { //FIX TO CURR
            if (selected.equals(randomKey)) {
                curr_score += 1;
            }
            total += 1;
            lessonText.setText("Score: " + curr_score + "/" + total + " ");
            //when counter reaches max, change activity
            Intent intent = new Intent(this, ShowScore.class);
            intent.putExtra("score", lessonText.getText().toString());
            startActivity(intent);
        }

        //checks if correct answer was given
        if (selected.equals(randomKey)) {
            curr_score += 1;
        }
        total += 1;
        old.add(randomKey); //adds key to different array so we don't repeat
        if (counter < curr.size()) { //to make sure we only loop until we exhaust list FIX TO CURR
            while (old.contains(randomKey)) { //so we don't use the same pictures
                random = new Random();
                randomKey = keys.get(random.nextInt(keys.size()));
                value = (int) curr.get(randomKey); //fix to curr
            }
            //counter increases to make sure we don't keep going forever
            counter += 1;
            //sets new image
            image.setImageResource(value);
            //updates score
            lessonText.setText("Score: " + curr_score + "/" + total + " ");
            //clear oldChoice list
            oldChoice.clear();
            oldChoice.add(randomKey);
            //clear old button text
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setText("");
            }
            //update buttons
            randButt = buttons.get(random.nextInt(4));
            randButt.setText(randomKey);
            for (int i = 0; i < buttons.size(); i++) {

                while (oldChoice.contains(randomChoice)) {
                    random = new Random();
                    randomChoice = keys.get( random.nextInt(keys.size()) );
                }
                if ( !buttons.get(i).getText().equals(randomKey) ) {
                    buttons.get(i).setText(randomChoice);
                    oldChoice.add(randomChoice);
                }
            }

        }
    }
}
