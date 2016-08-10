package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by USER on 03/08/2016.
 */
public abstract class Vaisseau extends ObjetEnMouvement
{
    protected int pointsDeVie;
    protected Context context;
    protected int vitesseTir;

    //La classe est abstraite et ne sera jamais instanciée, donc pas besoin de Get_Set.

    protected Vaisseau()
    {
        //Il faudra indiquer un sprite par défaut !
        sprite = null;
        pointsDeVie = 1;
    }

    protected Vaisseau(ImageView vaisseauSprite, int vaisseauPV, Context vaisseauContext)
    {
        sprite = vaisseauSprite;
        pointsDeVie = vaisseauPV;
        context = vaisseauContext;
        if (sprite != null) {
            sprite.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) sprite.getDrawable()).start();
                }
            });
        }
    }

    protected void perdrePV(int degatsSubits) {
        pointsDeVie -= degatsSubits;

        //On vérifie si le vaisseau est mort
        if (pointsDeVie <= 0)
            detruire();
        else {
            //Code pour faire clignoter.
        }

    }

    protected void detruire() {
        //Code
    }

    protected void tirer(final Point tailleEcran)
    {
        final Vaisseau vaisseau = this;
        int cadenceDeTir;
        if(this instanceof Joueur)
        {
            vitesseTir = 5;
            cadenceDeTir = 500;

        }
        else
        {
            vitesseTir = -5;
            cadenceDeTir = 2000;
        }

        TimerTask taskTirJoueur = new TimerTask() {
            @Override
            public void run() {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // this.tirer(tailleEcran);
                        new Missile(vaisseau, vitesseTir, 1, context, tailleEcran);
                    }
                });
            }
        };
        Timer tire = new Timer();
        tire.schedule(taskTirJoueur, 500, cadenceDeTir);
    }
}


