package checkbox.danieltilley.ie.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

    private CheckBox dog;
    private CheckBox cat;
    private CheckBox dragon;
    private Button showButton;
    private TextView showTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTextView = (TextView) findViewById(R.id.showTextId);
        showButton = (Button) findViewById(R.id.showButtonId);
        dog = (CheckBox) findViewById(R.id.checkBoxDogId);
        cat = (CheckBox) findViewById(R.id.checkBoxCatId);
        dragon = (CheckBox) findViewById(R.id.checkBoxDragonId);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //string builder object
                //can instanciate to build a set of strings
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(dog.getText().toString() + " Status is: " + dog.isChecked() + "\n");
                stringBuilder.append(cat.getText().toString() + " Status is: " + cat.isChecked() + "\n");
                stringBuilder.append(dragon.getText().toString() + " Status is: " + dragon.isChecked() + "\n");

                showTextView.setText(stringBuilder);
            }
        });


    }
}
