package david.nico.shoot.infinityspace;

import android.provider.ContactsContract;
import android.widget.ImageView;

/**
 * Created by USER on 03/08/2016.
 */
public class FondVisuel
{
    private int vitesseDefilement;
    private ImageView sprite;

    //region Get_Set
    public int getVitesseDefilement(){return vitesseDefilement;}
    public void setVitesseDefilement(int nouvelleVitesse)
    {
        vitesseDefilement = nouvelleVitesse;
    }

    public ImageView getSprite(){return sprite;}
    public void setSprite(ImageView nouveauSprite){sprite = nouveauSprite;}
    //endregion

    public FondVisuel()
    {
        //Il faut définir un sprite par défaut !
        sprite = null;
        vitesseDefilement = 1;
    }

    public FondVisuel(int fondVitesse, ImageView fondSprite)
    {
        vitesseDefilement = fondVitesse;
        sprite = fondSprite;
    }

    public void defiler()
    {
        //Code
    }
}
