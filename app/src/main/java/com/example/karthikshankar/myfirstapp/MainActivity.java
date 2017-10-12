/**
 * X-Cram Android Application Created by Karthik Shankar - 10.1.17
 * Supported by Mongo DB Team
 */
package com.example.karthikshankar.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        final Button mButton = (Button)findViewById(R.id.button);
        final EditText mEdit   = (EditText)findViewById(R.id.editText);
        message = mEdit.getText().toString();
       // if(message.charAt(0) == ' ' || message.charAt(message.length()-1) == ' ')


        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }


}
