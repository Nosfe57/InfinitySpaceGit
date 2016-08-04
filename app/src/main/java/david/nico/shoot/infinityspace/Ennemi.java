package david.nico.shoot.infinityspace;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;
import android.content.res.Resources;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by USER on 03/08/2016.
 */
public class Ennemi extends Vaisseau
{
    private short pointsScore;
    private Context context;

    //region Get_Set
    public short getPointsScore()
    {
        return pointsScore;
    }
    public void setPointsScore(short nouveauScore)
    {
        pointsScore = nouveauScore;
    }

    public Context getContext(){return context;}
    public void setContext(Context nouveauContext){context = nouveauContext;}
    //endregion

    public Ennemi()
    {
        super();
        pointsScore = 1;

        //ImageView test = new ImageView();
    }

    public Ennemi(ImageView ennemiSprite, int ennemiPV, short ennemiScore, Context ennemiContext)
    {
        super(ennemiSprite, ennemiPV);
        pointsScore = ennemiScore;
        context = ennemiContext;
    }

    @Override
    public void bouger(float y, int tailleFenetre)
    {

    }

    public void apparaitre()
    {
        ImageView image = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(100, 100);

    }


}
