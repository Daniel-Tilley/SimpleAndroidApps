package myactivity.danieltilley.ie.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView myTextView;
    private Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        myTextView = (TextView) findViewById(R.id.textView);
        goBackButton = (Button) findViewById(R.id.button);

        //retrieve data or "extras" from activity one
        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String myString = extras.getString("activityOne");

            myTextView.setText(myString);
        }//end if

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //return data
                Intent returnIntent = getIntent();
                returnIntent.putExtra("returnData", "From SecondActivity");
                returnIntent.putExtra("returnData1", "I'm Back again");
                setResult(RESULT_OK, returnIntent);

                //kill activity
                finish();

            }
        });
    }

}
