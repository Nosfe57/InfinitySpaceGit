package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
        Log.w("david", "objet créé " + this.getClass().toString());
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
                                    } else {
                                        objet.collisionMissileEnemiJoueur();
                                    }
                                }
                                if(objet instanceof Ennemi)
                                {
                                   objet.collisionEnemiAsteroide();
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

    public void collisionMissileEnemiJoueur()
    {
        ArrayList<ObjetEnMouvement> liste = getListeObjetsEnMouvement();
        for (ObjetEnMouvement object : liste)
        {
            if(object instanceof Joueur)
            {
                if (this.hitBox != null && ((Joueur) object).hitBox != null && this.sprite != null && ((Joueur) object).sprite != null)
                {
                    if(this.hitBox.intersect(((Joueur) object).hitBox))
                    {
                        listeObjetEnMouvement.remove(this);
                        timerMouvement.cancel();
                        timerMouvement.purge();
                        ((ViewGroup) sprite.getParent()).removeView(this.sprite);
                        this.sprite = null;
                        this.hitBox = null;
                        object = null;
                        finPartie();
                    }
                }
            }
            if(object instanceof Asteroide)
            {
                if (this.hitBox != null && ((Asteroide) object).hitBox != null && this.sprite != null && ((Asteroide) object).sprite != null)
                {
                    if (this.hitBox.intersect(((Asteroide) object).hitBox))
                    {
                        listeObjetEnMouvement.remove(this);
                        timerMouvement.cancel();
                        timerMouvement.purge();
                        ((ViewGroup) sprite.getParent()).removeView(this.sprite);
                        this.sprite = null;
                        this.hitBox = null;
                        object = null;
                    }
                }
            }
        }

        liste.clear();
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
            if(object instanceof Asteroide)
            {
                if (this.hitBox != null && ((Asteroide) object).hitBox != null && this.sprite != null && ((Asteroide) object).sprite != null)
                {
                    if (this.hitBox.intersect(((Asteroide) object).hitBox))
                    {
                        listeObjetEnMouvement.remove(this);
                        timerMouvement.cancel();
                        timerMouvement.purge();
                        ((ViewGroup) sprite.getParent()).removeView(this.sprite);
                        this.sprite = null;
                        this.hitBox = null;
                        object = null;
                    }

                }
            }
        }
        liste.clear();
    }
    public void collisionEnemiAsteroide()
    {
        ArrayList<ObjetEnMouvement> liste = getListeObjetsEnMouvement();
        for (ObjetEnMouvement object : liste)
        {
            if(object instanceof Asteroide)
            {
                if (this.hitBox != null && ((Asteroide) object).hitBox != null && this.sprite != null && ((Asteroide) object).sprite != null)
                {
                    if (this.hitBox.intersect(((Asteroide) object).hitBox)) {
                        listeObjetEnMouvement.remove(this);
                        timerMouvement.cancel();
                        timerMouvement.purge();
                        ((ViewGroup) sprite.getParent()).removeView(this.sprite);
                        this.sprite = null;
                        this.hitBox = null;
                        object = null;
                    }
                }
            }
        }
        liste.clear();
    }
    public void collisionJoueurEnemi()
    {
        ArrayList<ObjetEnMouvement> liste = getListeObjetsEnMouvement();
        for (ObjetEnMouvement object : liste)
        {
            if(object instanceof Ennemi)
            {
                if (this.hitBox != null && ((Ennemi) object).hitBox != null && this.sprite != null && ((Ennemi) object).sprite != null)
                {
                    if (this.hitBox.intersect(((Ennemi) object).hitBox))
                    {
                        ((Ennemi) object).hitBox = null;
                        finPartie();
                    }
                }
            }
            if(object instanceof Asteroide)
            {
                if (this.hitBox != null && ((Asteroide) object).hitBox != null && this.sprite != null && ((Asteroide) object).sprite != null)
                {
                    if (this.hitBox.intersect(((Asteroide) object).hitBox))
                    {
                        ((Asteroide) object).hitBox = null;
                        finPartie();
                    }
                }
            }
        }
        liste.clear();
    }

    public void finPartie()
    {
        Joueur.pointsDeVie--;
        if (Joueur.pointsDeVie == 0) {
            activity.finish();
            timerMouvement = null;

            Intent intent2 = new Intent();
            intent2.setClassName("david.nico.shoot.infinityspace", "david.nico.shoot.infinityspace.GameOver");
            intent2.putExtra("score", GameActivity.scoreActuel);


            activity.startActivity(intent2);

            GameActivity.scoreActuel = 0;
        }
    }

    public ArrayList<ObjetEnMouvement> getListeObjetsEnMouvement()
    {
        ArrayList<ObjetEnMouvement> liste = (ArrayList<ObjetEnMouvement>) listeObjetEnMouvement.clone();
        liste.remove(this);

        return liste;
    }


    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public void finalize()
    {
        Log.w("david", "objet détruit");
    }
}