package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.Log;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;
=======
import java.util.ArrayList;
>>>>>>> origin/master
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nico on 08/08/2016.
 */
public abstract class ObjetEnMouvement
{
    protected ImageView sprite;
    protected FrameLayout.LayoutParams layoutParams;
    protected Timer timerMouvement;
    protected Activity activity;
    protected Point tailleEcran;
    protected int vitesse; //Nombre négatif pour les ennemis et positif pour le joueur
    protected Rect hitBox;
    //protected static ArrayList<Ennemi> listeEnemi = new ArrayList<>();

    //Constructeur
    protected ObjetEnMouvement()
    {
        vitesse = -1;
    }


    public void bouger()
    {
        final ObjetEnMouvement objet = this;

        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                activity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (sprite != null)
                        {
                            if (layoutParams.leftMargin > 0 && layoutParams.leftMargin < 2000)
                            {
                                layoutParams.leftMargin += vitesse;
                                sprite.setLayoutParams(layoutParams);
                                hitBox.offsetTo(layoutParams.leftMargin, layoutParams.topMargin);

                                if (objet instanceof Missile)
                                {
                                    if (((Missile) objet).typeVaisseau)
                                    {
                                        objet.collisionMissileJoueurEnemi();
                                    }
                                }
                            }
                            else
                            {
                                timerMouvement.cancel();
                                timerMouvement.purge();
                                ((ViewGroup) sprite.getParent()).removeView(sprite);
                                sprite = null;

                                /*
                                if (objet instanceof Ennemi)
                                {
                                    listeEnemi.remove(objet);
                                }
                                */
                            }
                        }
                    }
                });
            }
        };
        timerMouvement = new Timer();
        timerMouvement.schedule(task, 0, 10);
    }

<<<<<<< HEAD
    public void collisionMissileJoueurEnemi()
    {

        /*
            for (Ennemi enemi: listeEnemi) {
                if (this.hitBox.intersect(enemi.hitBox))
                {
                    this.sprite = null;
                    enemi.sprite = null;
                    listeEnemi.remove(enemi);
                }
            }
        */
=======

    public ArrayList<ObjetEnMouvement> listeObjetsEnMouvement() {
        RelativeLayout globalLayout = (RelativeLayout) Resources.getSystem().getLayout(R.layout.activity_game);

        ArrayList<ObjetEnMouvement> liste = new ArrayList<>();

        int childCount = globalLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Object object = globalLayout.getChildAt(i);
        }
        return liste;
    }

    public boolean testColision()
    {


        return false;

>>>>>>> origin/master
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public boolean verifierCollision(Object obj)
    {


        return false;
    }

    public void finalize()
    {
        Log.w("david", "objet détruit");
    }
}