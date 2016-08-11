package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
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
    static Timer timerEnnemi;
    static Timer timerAsteroide;
    static Timer timerNettoyage;
    TimerTask vidage;
    TimerTask taskEnnemi;
    TimerTask taskAsteroide;
    RelativeLayout globalLayout;
    static TextView affichageScoreActuel;
    static int scoreActuel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        globalLayout = (RelativeLayout)findViewById(R.id.globalLayout);
        affichageScoreActuel = (TextView)findViewById(R.id.tv_scoreActuel);

        //Variable senseur du gyroscope
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //Récupère la taille de l'écran
        Display display = getWindowManager().getDefaultDisplay();
        tailleEcran = new Point();
        display.getSize(tailleEcran);



        affichageScoreActuel.setText(getString(R.string.scoreBarre, String.valueOf(0)));
    }

    public void deleteObjects()
    {
        ArrayList<Object> liste = new ArrayList<>();

        int childCount = globalLayout.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            Object object = globalLayout.getChildAt(i);
            liste.add(object);
        }

        for (Object obj : liste)
        {
            if(obj instanceof ObjetEnMouvement)
            {
                if (((ObjetEnMouvement)obj).sprite == null)
                {
                    obj = null;
                }
            }
        }
        System.gc();
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        final Context context = this;

        //on nullifie tous les objets pour eviter les bugs dans la new game
        for(ObjetEnMouvement objet : ObjetEnMouvement.listeObjetEnMouvement)
        {
            if(!(objet instanceof Joueur))
                objet = null;
        }
        ObjetEnMouvement.listeObjetEnMouvement.clear();

        Joueur.pointsDeVie = 1;
        joueur = new Joueur(null, 2, this, tailleEcran);
        joueur.tirer(tailleEcran);

        //Apparation des enemis
        taskEnnemi = new TimerTask() {
            @Override
            public void run() {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Ennemi ennemi = new Ennemi(null, 1, context, tailleEcran);
                        ennemi.tirer(tailleEcran);
                    }
                });
            }
        };
        timerEnnemi = new Timer();
        timerEnnemi.schedule(taskEnnemi, 500, 2000);

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
        timerAsteroide.schedule(taskAsteroide, 1000, 4000);

        vidage = new TimerTask() {
            @Override
            public void run() {
                deleteObjects();
            }
        };
        timerNettoyage = new Timer();
        timerNettoyage.schedule(vidage, 0, 2000);
    }

    public void arreterTimer()
    {
        if (joueur != null)
        {
            joueur.arreterTirs();
            joueur = null;
        }

        if (timerEnnemi != null)
        {
            timerEnnemi.cancel();
            timerEnnemi.purge();
            timerEnnemi = null;
        }

        if(timerAsteroide != null)
        {
            timerAsteroide.cancel();
            timerAsteroide.purge();
            timerAsteroide = null;
        }

        if(timerNettoyage != null)
        {
            timerNettoyage.cancel();
            timerNettoyage.purge();
            timerNettoyage = null;
        }

        vidage.cancel();
        taskAsteroide.cancel();
        taskEnnemi.cancel();
    }

    public void onPause()
    {
        super.onPause();
        arreterTimer();

    }

    public void onStop() {
        super.onStop();
        arreterTimer();
    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        public void onSensorChanged(SensorEvent event) {

            //float x = event.values[0];
            float y = event.values[1];

            if (joueur != null)
            {
                joueur.bouger(y, tailleEcran.y);
            }
        }
    };
}
