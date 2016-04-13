package seekbars.danieltilley.ie.seekbars;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView nicenessTextView;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nicenessTextView = (TextView) findViewById(R.id.showTextId);
        seekBar = (SeekBar) findViewById(R.id.seekBarId);

        nicenessTextView.setText("Niceness: " + seekBar.getProgress() + "/" + seekBar.getMax());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //when values change
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                nicenessTextView.setText("Niceness: " + progress + "/" + seekBar.getMax());
            }

            @Override
            //when user slides bar
            public void onStartTrackingTouch(SeekBar seekBar) {

                Toast.makeText(MainActivity.this, "User Started moving the bar", Toast.LENGTH_SHORT).show();
            }

            @Override
            //when user stops touching bar
            public void onStopTrackingTouch(SeekBar seekBar) {

                Toast.makeText(MainActivity.this, "User Stopped moving the bar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
