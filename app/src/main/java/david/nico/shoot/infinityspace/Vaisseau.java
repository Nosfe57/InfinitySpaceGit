package david.nico.shoot.infinityspace;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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

    protected void tirer(Point tailleEcran)
    {
        if(this instanceof Joueur)
        {
            vitesseTir = 5;
        }
        else
        {
            vitesseTir = -1;
        }
        Missile tir = new Missile(this, vitesseTir, 1, context);
    }
}


