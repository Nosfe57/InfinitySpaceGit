package david.nico.shoot.infinityspace;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by USER on 03/08/2016.
 */
public class Joueur extends Vaisseau
{
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
        super(joueurSprite, joueurPV, playerContext);
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


    public void bouger(float y, int tailleFenetre)
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
