package alertdialogs.danieltilley.ie.alertdialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button showDialog;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialog = (Button) findViewById(R.id.showButtonId);
        showDialog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //show our alert dialog
                dialog = new AlertDialog.Builder(MainActivity.this);

                //set title
                dialog.setTitle(getResources().getString(R.string.dialogTitle));

                //set message
                dialog.setMessage(getResources().getString(R.string.dialogMessage));

                //set cancellable
                dialog.setCancelable(false);

                //set an icon
                dialog.setIcon(android.R.drawable.btn_star);
                
                //set buttons is dialog
                //positive
                dialog.setPositiveButton(getResources().getString(R.string.possitiveButton),

                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //exit activity
                                MainActivity.this.finish();

                            }
                        });

                //negative
                dialog.setNegativeButton(getResources().getString(R.string.negativeButton),

                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });

                //create dialog
                AlertDialog alertD = dialog.create();

                //show dialog when button is clicked
                alertD.show();
            }
        });

    }
}
