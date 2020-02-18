package com.example.lab3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button one, two, three, four;
    int oneCount, twoCount, threeCount, fourCount;
    Map<String, String> qa;
    SharedPreferences pref;
    Integer[] scores;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        super.onCreate(savedInstanceState);
        qa = new HashMap<String, String>();
        qa.put("one", "1");
        qa.put("two", "2");
        qa.put("three", "3");
        qa.put("four", "4");
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        scores = new Integer[4];
        scores[0] = pref.getInt("one", -1);
        scores[1] = pref.getInt("two", -1);
        scores[2] = pref.getInt("three", -1);
        scores[3] = pref.getInt("four", -1);
        for(int x=0; x<scores.length; x++)
            if( scores[x] == null )
                scores[x] = 0;
        setContentView(R.layout.activity_main);
    }

    public void addVote(View view) {
        String num = view.getResources().getResourceName(view.getId()).trim();
        num = num.substring(num.indexOf('/')+1);
//        Log.i("test", "" + num);
//        Log.i("testScore", "" + Integer.parseInt(qa.get(num)));
        scores[Integer.parseInt(qa.get(num))-1]++;
        Log.i("scores", "" + scores[Integer.parseInt(qa.get(num))-1]);
        editor.putInt(num, scores[Integer.parseInt(qa.get(num))-1]);
        editor.commit();
        Context context = getApplicationContext();
        CharSequence text = num + ": " +  scores[Integer.parseInt(qa.get(num))-1];
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
