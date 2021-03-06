package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
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
        layoutParams = new FrameLayout.LayoutParams(dpToPx(40), dpToPx(40));
        Random positionRandom = new Random();

        //30dp : hauteur de la barre des scores,
        //70dp = 30dp de la barre des scores et 40 dp hauteur des astéroides et des vaisseaux enemi
        int y = positionRandom.nextInt(tailleEcran.y - dpToPx(70)) + dpToPx(30);
        layoutParams.topMargin = y;
        layoutParams.leftMargin = tailleEcran.x;

        hitBox = new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height);

        //Ajout de l'ImageView dans mon layout
        Activity activity = (Activity) context;
        activity.addContentView(sprite, layoutParams);
    }
}