package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by USER on 03/08/2016.
 */
public class Missile extends ObjetEnMouvement
{
    protected int degatsInfliges;
    protected Context context;
    private Vaisseau vaisseau;

    public boolean typeVaisseau; //true pour le vaisseau joueur et false pour le vaisseau enemi

    //region Get_Set
    public ImageView getSprite(){return sprite;}
    public void setSprite(ImageView nouveauSprite){sprite = nouveauSprite;}

    public int getVitesse(){return vitesse;}
    public void setVitesse(int nouvelleVitesse){vitesse = nouvelleVitesse;}

    public int getDegatsInfliges(){return degatsInfliges;}
    public void setDegatsInfliges(int nouveauxDegatsInfliges){degatsInfliges = nouveauxDegatsInfliges;}
    //endregion

    public Missile()
    {
        /*Il faut mettre un sprite par défaut !*/
        super();
    }

    public Missile(Vaisseau tireur, int missileVitesse, int missileDegatsInfliges, Context missileContext, Point missileTailleEcran)
    {
        context = missileContext;
        activity = (Activity) context;
        vaisseau = tireur;
        degatsInfliges = missileDegatsInfliges;
        vitesse = missileVitesse;
        tailleEcran = missileTailleEcran;

        apparaitre(tireur);
        bouger();
    }

    private void apparaitre(Vaisseau vaisseau)
    {
        //ImageView de l'objet
        sprite = new ImageView(context);
        if (vaisseau instanceof Joueur)
        {
            typeVaisseau = true;
            sprite.setImageResource(R.drawable.bullet);
        }
        else if(vaisseau instanceof Ennemi)
        {
            typeVaisseau = false;
            sprite.setImageResource(R.drawable.bullet_red);
        }

        //Parametre du vaisseau qui tir
        FrameLayout.LayoutParams paramVaisseau = (FrameLayout.LayoutParams) vaisseau.sprite.getLayoutParams();

        //parametre du missile
        layoutParams = new FrameLayout.LayoutParams(dpToPx(10), dpToPx(10));
        layoutParams.topMargin = paramVaisseau.topMargin + (paramVaisseau.height / 2) - dpToPx(5);
        if (vaisseau instanceof Joueur)
            layoutParams.leftMargin = paramVaisseau.leftMargin + paramVaisseau.width;
        Activity activity = (Activity) context;
        if(vaisseau instanceof Ennemi)
            layoutParams.leftMargin = paramVaisseau.leftMargin;
        activity.addContentView(sprite, layoutParams);


        hitBox = new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height);

    }

    public void detruire()
    {
<<<<<<< HEAD
        //Code
=======
>>>>>>> origin/master

    }
}
