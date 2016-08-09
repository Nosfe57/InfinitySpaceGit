package david.nico.shoot.infinityspace;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.Serializable;

/**
 * Created by USER on 03/08/2016.
 */
public class Joueur extends Vaisseau
{
    private Context context;
    private int nbBombes;
    private int scoreActuel;

    //region Get_Set
    public int getNbBombes()
    {
        return nbBombes;
    }
    public void setNbBombes(int modifNbBombes)
    {
        nbBombes += modifNbBombes;
    }

    public int getScore()
    {
        return scoreActuel;
    }
    public void setScore(int modifScore)
    {
        scoreActuel += modifScore;
    }
    //endregion

    public Joueur()
    {
        super();
        nbBombes = 2;
        scoreActuel = 0;
    }

    public Joueur(ImageView joueurSprite, int joueurPV, int joueurNbBombes, Context playerContext)
    {
        super(joueurSprite, joueurPV);
        context = playerContext;
        nbBombes = joueurNbBombes;
        scoreActuel = 0;
    }

    public void utiliserBombe()
    {
        if (nbBombes > 0)
        {
            //Code
        }
    }

    @Override
    public void tirer(Point tailleEcran)
    {
        //Code
        //param du sprite
        RelativeLayout.LayoutParams positionSprite = (RelativeLayout.LayoutParams) sprite.getLayoutParams();

        ImageView bullet = new ImageView(context);
        bullet.setImageResource(R.drawable.bullet);

        FrameLayout.LayoutParams bulletParams = new FrameLayout.LayoutParams((int) (10 * Resources.getSystem().getDisplayMetrics().density), (int) (10 * Resources.getSystem().getDisplayMetrics().density));
        bulletParams.topMargin = positionSprite.topMargin + (sprite.getHeight() / 2);
        bulletParams.leftMargin = positionSprite.leftMargin + positionSprite.width;
        Activity activity = (Activity) context;
        activity.addContentView(bullet, bulletParams);
    }

    @Override
    public  void bouger(){

    }

    public void move(float y, int tailleFenetre)
    {
        if (y != 0) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(sprite.getWidth(), sprite.getHeight());
            params.topMargin = sprite.getTop() + (int) (y * -90.0f);
            if(params.topMargin < 0)
                params.topMargin = 0;
            if(params.topMargin > (tailleFenetre - sprite.getHeight()) )
                params.topMargin = (tailleFenetre - sprite.getHeight());
            params.leftMargin = sprite.getLeft();
            sprite.setLayoutParams(params);
        }
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
