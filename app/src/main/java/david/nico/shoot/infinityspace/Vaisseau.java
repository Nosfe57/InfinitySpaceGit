package david.nico.shoot.infinityspace;

import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by USER on 03/08/2016.
 */
public abstract class Vaisseau
{
    protected ImageView sprite;
    protected int pointsDeVie;

    //La classe est abstraite et ne sera jamais instanciée, donc pas besoin de Get_Set.

    protected Vaisseau()
    {
        //Il faudra indiquer un sprite par défaut !
        sprite = null;
        pointsDeVie = 1;
    }

    protected Vaisseau(ImageView vaisseauSprite, int vaisseauPV)
    {
        sprite = vaisseauSprite;
        pointsDeVie = vaisseauPV;

        if (sprite != null) {
            sprite.post(new Runnable() {
                @Override
                public void run() {
                    ((AnimationDrawable) sprite.getDrawable()).start();
                }
            });
        }
    }

    protected void perdrePV(int degatsSubits)
    {
        pointsDeVie -= degatsSubits;

        //On vérifie si le vaisseau est mort
        if (pointsDeVie <= 0)
            detruire();
        else
        {
            //Code pour faire clignoter.
        }

    }

    protected void detruire()
    {
        //Code
    }

    protected abstract void tirer(Point tailleEcran);


    public abstract void bouger();
}
