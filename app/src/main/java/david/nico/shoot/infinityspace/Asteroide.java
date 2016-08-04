package david.nico.shoot.infinityspace;

import android.widget.ImageView;

import java.util.Random;

/**
 * Created by USER on 03/08/2016.
 */
public class Asteroide extends Missile
{
    public Asteroide()
    {
        //Il faut mettre un sprite par défaut !
        sprite = null;
        degatsInfliges = 1000;
        Random rnd = new Random();
        vitesse = (rnd.nextInt(10) + 1);
    }

    public Asteroide(ImageView asteroideSprite, int asteroideVitesse, int asteroideDegatsInfligés)
    {
        sprite = asteroideSprite;
        vitesse = asteroideVitesse;
        degatsInfliges = asteroideDegatsInfligés;
    }
}