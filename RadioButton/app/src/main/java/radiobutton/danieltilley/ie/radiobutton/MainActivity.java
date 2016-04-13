package radiobutton.danieltilley.ie.radiobutton;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView showTextView;
    private Button showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTextView = (TextView) findViewById(R.id.showTextId);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupId);

        showButton = (Button) findViewById(R.id.showButtonId);
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get id of the button selected
                int selectedOption = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedOption);

                showTextView.setText(radioButton.getText());
            }
        });
    }
}
