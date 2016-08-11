package david.nico.shoot.infinityspace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GameOver extends AppCompatActivity
{
    EditText entrerPseudo;
    Button validerPseudo;
    Bdd data;
    int scoreFinPartie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_over);

        entrerPseudo = (EditText)findViewById(R.id.txt_entrerPseudo);
        validerPseudo = (Button)findViewById(R.id.btn_validerPseudo);
        data = new Bdd(getApplicationContext());
        scoreFinPartie = getIntent().getIntExtra("score", 1);

        validerPseudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (!(entrerPseudo.getText().toString()).equals(""))
                {
                    data.insertScore(entrerPseudo.getText().toString(), scoreFinPartie);
                    Intent intent = new Intent(GameOver.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Merci de rentrer un pseudo", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }
}
