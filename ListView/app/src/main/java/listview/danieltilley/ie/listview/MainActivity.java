package listview.danieltilley.ie.listview;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listViewId);

        final String[] values = new String[]{
                "a","b","c","d","e","f"
        };

        //adapter to display list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                //get app context
                getApplicationContext(),
                //get qa layout pre made by android
                android.R.layout.simple_list_item_1,
                //text layout
                android.R.id.text1,
                //array of values
                values);

        //assign the adapter to list view
        listView.setAdapter(adapter);


        //set on click lister for each item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //keep track of position in list
                int intPosition = position;
                //retrieve string to display on screen
                String clickedValue =  listView.getItemAtPosition(intPosition).toString();

                Toast.makeText(getApplicationContext(), clickedValue, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
