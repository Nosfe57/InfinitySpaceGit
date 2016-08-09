package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by USER on 03/08/2016.
 */
public class Asteroide extends ObjetEnMouvement
{
    private Point tailleEcran;
    private Context context;

    public Asteroide(Context contextActivity, Point appTailleEcran)
    {
        super();
        context = contextActivity;
        activity = (Activity) context;
        tailleEcran = appTailleEcran;
        creationSprite();
        bouger();
    }

    private void creationSprite()
    {
        //Nombre aléatoire pour choisir l'image de l'astéroide
        Random rand = new Random();
        int image = rand.nextInt(4) + 1;

        sprite = new ImageView(context);
        if (image == 1)
        {
            sprite.setImageResource(R.drawable.aestroid_1);
        }
        else if (image == 2)
        {
            sprite.setImageResource(R.drawable.aestroid_2);
        }
        else if (image == 3)
        {
            sprite.setImageResource(R.drawable.aestroid_3);
        }
        else if (image == 4)
        {
            sprite.setImageResource(R.drawable.aestroid_4);
        }
        else {
            sprite.setImageResource(R.drawable.aestroid_1);
        }

        //Paramètres de l'imageView
        layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        Random positionRandom = new Random();
        int y = positionRandom.nextInt(tailleEcran.y - sprite.getHeight());
        layoutParams.topMargin = y;
        layoutParams.leftMargin = tailleEcran.x -200;

        Activity activity = (Activity) context;
        activity.addContentView(sprite, layoutParams);
    }
}