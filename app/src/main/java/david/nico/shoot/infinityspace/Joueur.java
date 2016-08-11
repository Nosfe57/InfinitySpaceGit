package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by USER on 03/08/2016.
 */
public class Joueur extends Vaisseau
{
    private int nbBombes;
    public static int pointsDeVie;

    //region Get_Set
    public int getNbBombes()
    {
        return nbBombes;
    }
    public void setNbBombes(int modifNbBombes)
    {
        nbBombes += modifNbBombes;
    }
    

    //endregion

    public Joueur()
    {
        super();
        nbBombes = 2;
        pointsDeVie = 1;
    }

    public Joueur(ImageView joueurSprite, int joueurNbBombes, Context playerContext, Point joueurTailleEcran)
    {
        super(joueurSprite, playerContext);
        nbBombes = joueurNbBombes;
        tailleEcran = joueurTailleEcran;
        apparaitre();
        pointsDeVie = 1;
    }

    public void utiliserBombe()
    {
        if (nbBombes > 0)
        {

        }
    }

    public void apparaitre()
    {
        sprite = new ImageView(context);
        sprite.setImageResource(R.drawable.player_animation);

        layoutParams = new FrameLayout.LayoutParams(dpToPx(40), dpToPx(40));
        layoutParams.topMargin = tailleEcran.y / 2 - dpToPx(20);
        layoutParams.leftMargin = dpToPx(40);

        activity = (Activity)context;
        activity.addContentView(sprite, layoutParams);

        hitBox = new Rect(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + layoutParams.width, layoutParams.topMargin + layoutParams.height);

        sprite.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable anim = (AnimationDrawable) sprite.getDrawable();
                anim.start();
            }
        });
    }

    public void bouger(float y, int tailleFenetre)
    {
        if (y != 0) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(sprite.getWidth(), sprite.getHeight());
            params.topMargin = sprite.getTop() + (int) (y * -90.0f);
            if(params.topMargin < dpToPx(30))
                params.topMargin = dpToPx(30);
            if(params.topMargin > (tailleFenetre - sprite.getHeight()) )
                params.topMargin = (tailleFenetre - sprite.getHeight());
            params.leftMargin = sprite.getLeft();
            sprite.setLayoutParams(params);
            hitBox.offsetTo(params.leftMargin, params.topMargin);

        }

        this.collisionJoueurEnemi();

        /*Log.w("nico", "after : " + sprite.getTop());
        if ( (int) y >0 || (int) y < 0) {

            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f, 0.0f , y * -100.0f);
            animation.setDuration(100);
            animation.setFillAfter(true);
            sprite.startAnimation(animation);

            Log.w("nico", "before : " + spaceship.getTop());
        }*/
    }
}
