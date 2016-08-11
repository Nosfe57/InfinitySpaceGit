package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.Log;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;

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
    Activity activity;
    protected Point tailleEcran;
    protected int vitesse; //Nombre négatif pour les ennemis et positif pour le joueur
    protected Rect hitBox;
    protected static ArrayList<ObjetEnMouvement> listeObjetEnMouvement = new ArrayList<>();

    //Constructeur
    protected ObjetEnMouvement()

    {
        listeObjetEnMouvement.add(this);
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
                        if (sprite != null && hitBox != null)
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
                                listeObjetEnMouvement.remove(objet);
                            }
                        }
                    }
                });
            }
        };
        timerMouvement = new Timer();
        timerMouvement.schedule(task, 0, 10);
    }


    public void collisionMissileJoueurEnemi()
    {
        ArrayList<ObjetEnMouvement> liste = getListeObjetsEnMouvement();
        for (ObjetEnMouvement object : liste)
        {
            if (object instanceof Ennemi)
                {
                if (this.hitBox != null && ((Ennemi) object).hitBox != null && this.sprite != null && ((Ennemi) object).sprite != null)
                {
                    if (this.hitBox.intersect(((Ennemi) object).hitBox))
                    {
                        listeObjetEnMouvement.remove(this);
                        listeObjetEnMouvement.remove(object);
                        timerMouvement.cancel();
                        timerMouvement.purge();
                        ((Ennemi) object).timerMouvement.cancel();
                        ((Ennemi) object).timerMouvement.purge();
                        ((Ennemi) object).anim.stop();


                        ((ViewGroup) sprite.getParent()).removeView(((Ennemi) object).sprite);
                        ((ViewGroup) sprite.getParent()).removeView(this.sprite);
                       // ViewGroup globalLayout = (ViewGroup) ((Ennemi) object).activity.findViewById(R.id.globalLayout);
                       // globalLayout.removeView(((Ennemi) object).sprite);

                        this.sprite = null;
                        this.hitBox = null;
                        ((Ennemi) object).sprite = null;
                        ((Ennemi) object).hitBox = null;
                        object = null;
                        GameActivity.scoreActuel++;
                        GameActivity.affichageScoreActuel.setText(activity.getString(R.string.scoreBarre, String.valueOf(GameActivity.scoreActuel)));
                    }
                }
            }
        }
        liste.clear();
    }

    public ArrayList<ObjetEnMouvement> getListeObjetsEnMouvement()
    {
        ArrayList<ObjetEnMouvement> liste = (ArrayList<ObjetEnMouvement>) listeObjetEnMouvement.clone();
        liste.remove(this);
        /*
        RelativeLayout globalLayout = (RelativeLayout) activity.findViewById(R.id.globalLayout);
        //RelativeLayout globalLayout = (RelativeLayout) Resources.getSystem().getLayout(R.layout.activity_game);

        ArrayList<ObjetEnMouvement> liste = new ArrayList<>();

        int childCount = globalLayout.getChildCount();
        Log.w("nico", "nombre d'objet dans ma liste : " + childCount);
        for (int i = 0; i < childCount; i++)
        {
            Object object = globalLayout.getChildAt(i);
            if (object instanceof ObjetEnMouvement)
                liste.add((ObjetEnMouvement) object);
        }
        */
        return liste;
    }

    public boolean testColision()
    {


        return false;


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