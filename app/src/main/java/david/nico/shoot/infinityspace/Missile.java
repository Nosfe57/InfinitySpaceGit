package david.nico.shoot.infinityspace;

import android.widget.ImageView;

/**
 * Created by USER on 03/08/2016.
 */
public class Missile
{
    protected ImageView sprite;
    protected int vitesse;
    protected int degatsInfliges;

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
        sprite = null;
        vitesse = 1;
        degatsInfliges = 1;
    }

    public Missile(ImageView missileSprite, int missileVitesse, int missileDegatsInfliges)
    {
        sprite = missileSprite;
        vitesse = missileVitesse;
        degatsInfliges = missileDegatsInfliges;
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
