package david.nico.shoot.infinityspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ScoresActivity extends AppCompatActivity {

    TextView scores;
    Button reset;
    Bdd base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_scores);

        base = new Bdd(getApplicationContext());

        reset = (Button)findViewById(R.id.btn_resetScores);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base.clearScores();
                afficherScores();
            }
        });

        afficherScores();
    }

    public void afficherScores()
    {
        ArrayList<Score> listeScores = base.getScores();
        scores = (TextView)findViewById(R.id.txt_scores);

        String temp = "";

        if (!listeScores.isEmpty())
        {
            for (Score score : listeScores)
            {
                temp += score.getPseudo() + " : " +  String.valueOf(score.getValeur()) + "\r\n";
            }
        }
        else {
            temp = "Aucun score";
        }
        scores.setText(temp);
    }
}
