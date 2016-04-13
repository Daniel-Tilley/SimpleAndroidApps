package bios.danieltilley.ie.bios;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {


    //variables
    private ImageView bachImage;
    private ImageView mozartImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bachImage = (ImageView) findViewById(R.id.bachImageId);
        mozartImage = (ImageView) findViewById(R.id.mozartImageId);

        bachImage.setOnClickListener(this);
        mozartImage.setOnClickListener(this);
    }//end on create

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bachImageId:{

                Intent bachIntent = new Intent(MainActivity.this, DetailsActivity.class);
                bachIntent.putExtra("bach", "Guess whos bach? bach again!");
                bachIntent.putExtra("name", "bach");
                startActivity(bachIntent);

                break;
            }

            case R.id.mozartImageId:{

                Intent mozartIntent = new Intent(MainActivity.this, DetailsActivity.class);
                mozartIntent.putExtra("mozart", "They call me Wolfgang, my bitches do anyway");
                mozartIntent.putExtra("name", "mozart");
                startActivity(mozartIntent);

                break;
            }
        }
    }
}
