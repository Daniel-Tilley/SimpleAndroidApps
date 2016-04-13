package myactivity.danieltilley.ie.myactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    //request code for returning data
    static final int REQUEST_CODE = 1;

    private Button myButton;

    //first method to make

    @Override
    //bundle holds instance states
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button) findViewById(R.id.buttonId);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                //pass data to second activity
                intent.putExtra("activityOne", "Hello Activity two");

                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }//end on create

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){

                //make sure keys match
                String result = data.getStringExtra("returnData");

                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            }
        }
    }


}
