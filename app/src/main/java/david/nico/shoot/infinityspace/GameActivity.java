package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nico on 28/07/2016.
 */
public class GameActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    Point tailleEcran;
    Joueur joueur;
    Timer timerEnnemis;
    Timer timerAsteroide;
    Timer timerNettoyage;
    RelativeLayout globalLayout;
    TextView tvScoreActuel;
    TimerTask ennemis;
    TimerTask taskAsteroide;
    TimerTask vidage;
    Button pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        globalLayout = (RelativeLayout)findViewById(R.id.globalLayout);
        tvScoreActuel = (TextView)findViewById(R.id.tv_scoreActuel);
        pause = (Button)findViewById(R.id.btn_pause);

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jeuEnPause();
            }
        });

        //Variable senseur du gyroscope
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //Récupère la taille de l'écran
        Display display = getWindowManager().getDefaultDisplay();
        tailleEcran = new Point();
        display.getSize(tailleEcran);

        joueur = new Joueur(null, 3, 2, this, tailleEcran);
        joueur.tirer(tailleEcran);

        final Context context = this;

        //Apparation des enemis
        ennemis = new TimerTask() {
            @Override
            public void run() {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Ennemi ennemi = new Ennemi(null, 1, 1, context, tailleEcran);
                        ennemi.tirer(tailleEcran);
                    }
                });
            }
        };
        timerEnnemis = new Timer();


        //Apparition des astéroides
        taskAsteroide = new TimerTask() {
            @Override
            public void run() {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Asteroide asteroide = new Asteroide(context, tailleEcran);
                    }
                });
            }
        };
        timerAsteroide = new Timer();


        vidage = new TimerTask() {
            @Override
            public void run() {
                deleteObjects();
            }
        };
        timerNettoyage = new Timer();


        jeuReprendre();
        rafraichirScoreActuel();
    }

    public ArrayList<ObjetEnMouvement> listeObjetsEnMouvement()
    {
        ArrayList<ObjetEnMouvement> liste = new ArrayList<>();

        int childCount = globalLayout.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            Object object = globalLayout.getChildAt(i);
        }
        return liste;
    }

    public void deleteObjects()
    {
        for (ObjetEnMouvement obj : listeObjetsEnMouvement())
        {
            if(obj.sprite == null)
            {
                obj = null;
            }
        }
        System.gc();

    }

    public void rafraichirScoreActuel()
    {
        tvScoreActuel.setText(getString(R.string.scoreBarre, String.valueOf(joueur.getScore())));
    }

    public void onResume()
    {
        super.onResume();
        sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);


        //jeuReprendre();

    }

    public void jeuReprendre()
    {
        timerEnnemis.schedule(ennemis, 500, 2000);
        timerAsteroide.schedule(taskAsteroide, 1000, 4000);
        timerNettoyage.schedule(vidage, 5000, 5000);
    }

    public void jeuEnPause()
    {
        synchronized(listeObjetsEnMouvement())
        {
            for (ObjetEnMouvement obj : listeObjetsEnMouvement())
            {
                try {
                    obj.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);

        jeuEnPause();
    }

    public SensorEventListener gyroListener = new SensorEventListener()
    {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        public void onSensorChanged(SensorEvent event)
        {

            //float x = event.values[0];
            float y = event.values[1];

            joueur.bouger(y, tailleEcran.y);
        }
    };
}
