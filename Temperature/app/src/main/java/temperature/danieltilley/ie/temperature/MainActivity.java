package temperature.danieltilley.ie.temperature;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText tempEditText;
    private Button celButton;
    private Button fahButton;
    private TextView showTempTextView;

    //rouding numbers
    DecimalFormat round = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tempEditText = (EditText) findViewById(R.id.enterTextId);
        celButton = (Button) findViewById(R.id.celciusButtonId);
        fahButton = (Button) findViewById(R.id.fahreheitButtonId);
        showTempTextView = (TextView) findViewById(R.id.showText);

        //setup buttons
        celButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editTextVal = tempEditText.getText().toString();


                if (editTextVal.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter a value", Toast.LENGTH_SHORT).show();
                }

                else {
                    double convertedVal = Double.parseDouble(editTextVal);
                    double result = convertToFah(convertedVal);

                    String toShow = String.valueOf(round.format(result)) + " C";

                    showTempTextView.setText(toShow);
                }
            }
        });

        fahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editTextVal = tempEditText.getText().toString();


                if (editTextVal.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter a value", Toast.LENGTH_SHORT).show();
                }

                else {
                    double convertedVal = Double.parseDouble(editTextVal);
                    double result = convertToCelcius(convertedVal);

                    String toShow = String.valueOf(round.format(result)) + " F";

                    showTempTextView.setText(toShow);
                }
            }
        });
    }

    //methods

    public double convertToCelcius(double farVal){
        double resultCel = (farVal * 9/5) + 32;

        return resultCel;
    }

    public double convertToFah (double celVal){
        double resultFah = (celVal - 32) * 5/9;

        return resultFah;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
