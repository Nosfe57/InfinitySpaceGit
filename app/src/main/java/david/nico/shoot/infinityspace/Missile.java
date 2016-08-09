package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
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

    public Missile(Vaisseau tireur, int missileVitesse, int missileDegatsInfliges, Context missileContext)
    {
        context = missileContext;
        sprite = new ImageView(context);
        activity = (Activity) context;
        degatsInfliges = missileDegatsInfliges;
        vitesse = missileVitesse;
        if (tireur instanceof Joueur)
        {
            sprite.setImageResource(R.drawable.bullet);
        }
        else if(tireur instanceof Ennemi)
        {
            sprite.setImageResource(R.drawable.bullet);
        }
        apparaitre(tireur);
        bouger();
    }

    private void apparaitre(Vaisseau vaisseau)
    {
        //param du sprite

        RelativeLayout.LayoutParams paramVaisseau = (RelativeLayout.LayoutParams) vaisseau.sprite.getLayoutParams();

        //sprite.setImageResource(R.drawable.bullet);

        layoutParams = new FrameLayout.LayoutParams(dpToPx(10), dpToPx(10));
        layoutParams.topMargin = paramVaisseau.topMargin + (paramVaisseau.height / 2) - dpToPx(5);
        layoutParams.leftMargin = paramVaisseau.leftMargin + paramVaisseau.width;
        Activity activity = (Activity) context;
        activity.addContentView(sprite, layoutParams);
    }

    public boolean verifierColision(Joueur joueur)
    {
        //Code
        return false;
    }

    public boolean verifierColision(Ennemi ennemi)
    {
        //Code
        return false;
    }

    public void detruire()
    {
        //Code
    }
}
