package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by USER on 03/08/2016.
 */
public class Asteroide extends Missile
{
    private Context context;


    public Asteroide()
    {
        //Il faut mettre un sprite par défaut !
        sprite = null;
        degatsInfliges = 1000;
        Random rnd = new Random();
        vitesse = (rnd.nextInt(10) + 1);
    }

    public Asteroide(Context contextActivity)
    {
        context = contextActivity;
        vitesse = 100;
        degatsInfliges = 1000;
        creationSprite();
    }

    private void creationSprite()
    {
        //Nombre aléatoire pour choisir l'image de l'astéroide
        Random rand = new Random();
        int image = rand.nextInt(4) + 1;

        ImageView asteroide = new ImageView(context);
        asteroide.setImageResource(R.drawable.aestroid_1);
        /*
        switch (image)
        {
            case 1:
                asteroide.setImageResource(R.drawable.aestroid_1);
                break;
            case 2:
                asteroide.setImageResource(R.drawable.aestroid_2);
                break;
            case 3:
                asteroide.setImageResource(R.drawable.aestroid_3);
                break;
            case 4:
                asteroide.setImageResource(R.drawable.aestroid_4);
                break;
            default:
                asteroide.setImageResource(R.drawable.aestroid_1);
                break;
        }
        */
        //Paramétre de l'imageView
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(200,100,0,0);


        Activity activity = (Activity) context;
        activity.addContentView(asteroide, params);
    }
}