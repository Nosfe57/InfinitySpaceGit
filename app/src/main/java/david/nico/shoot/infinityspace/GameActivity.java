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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nico on 28/07/2016.
 */
public class GameActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    ImageView spaceship;
    Point tailleEcran;
    Joueur joueur;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        //Variable senseur du gyroscope
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        //ImageView du joueur
        spaceship = (ImageView) findViewById(R.id.spaceship);

        joueur = new Joueur(spaceship, 3, 2, this);
        joueur.tirer(tailleEcran);

        //Récupère la taille de l'écran et l'enregistre dans tailleEcran
        Display display = getWindowManager().getDefaultDisplay();
        tailleEcran = new Point();
        display.getSize(tailleEcran);

        final Context context = this;

        TimerTask essai = new TimerTask() {
            @Override
            public void run() {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Ennemi ennemi = new Ennemi(null, 1, 1, context, tailleEcran);
                        //ennemi.tirer(tailleEcran);
                        Asteroide asTest = new Asteroide(context, tailleEcran);
                    }
                });
            }
        };

        timer = new Timer();
        timer.schedule(essai, 500, 2000);

        TimerTask taskTirJoueur = new TimerTask() {
            @Override
            public void run() {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        joueur.tirer(tailleEcran);
                    }
                });
            }
        };

        Timer timerTirJoueur = new Timer();
        timerTirJoueur.schedule(taskTirJoueur, 500, 500);
    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(gyroListener);

    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) { }

        public void onSensorChanged(SensorEvent event) {

            //float x = event.values[0];
            float y = event.values[1];

            joueur.bouger(y, tailleEcran.y);
        }
    };
}
