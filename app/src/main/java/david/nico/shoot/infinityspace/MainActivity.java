package david.nico.shoot.infinityspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNewGame;
    Button viewScores;
    Button options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btnNewGame = (Button)findViewById(R.id.btn_new_game);
        btnNewGame.setOnClickListener(mainClick);

        options = (Button)findViewById(R.id.btn_options);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                /*Intent intent = new Intent(MainActivity.this, GameOver.class);
                intent.putExtra("score", 50);
                startActivity(intent);*/
            }
        });

        viewScores = (Button)findViewById(R.id.btn_viewScores);
        viewScores.setOnClickListener(scoresClick);
    }

    //event
    private View.OnClickListener mainClick = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener scoresClick = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
            startActivity(intent);
        }
    };
}
