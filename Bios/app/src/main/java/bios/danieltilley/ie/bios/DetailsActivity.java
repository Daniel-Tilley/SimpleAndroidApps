package bios.danieltilley.ie.bios;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends Activity {

    private ImageView profileImage;
    private TextView bioText;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        profileImage = (ImageView) findViewById(R.id.displayImageId);
        bioText = (TextView) findViewById(R.id.displayTextId);

        extras = getIntent().getExtras();

        if (extras != null){

            String name = extras.getString("name");

            showDetails(name);
        }
    }

    public void showDetails (String mName){

        if (mName.equals("bach")){

            profileImage.setImageDrawable(getResources().getDrawable(R.drawable.bach));
            bioText.setText(extras.getString(mName));
        }

        else if (mName.equals("mozart")){

            profileImage.setImageDrawable(getResources().getDrawable(R.drawable.mozart));
            bioText.setText(extras.getString(mName));
        }
    }
}
