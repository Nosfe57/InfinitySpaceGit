package david.nico.shoot.infinityspace;

import java.util.prefs.Preferences;

/**
 * Created by USER on 03/08/2016.
 */
public class Options
{
    private short difficulte; //Facile = 3 ; Moyen = 2 ; Difficile = 1
    private short volumeMusique; //De 1 à 10
    private short volumeEffets; //De 1 à 10
    private boolean orientation; //False = horizontal ; True = vertical

    //region Get_Set
    public short getDifficulte(){return difficulte;}
    public void setDifficulte(short nouvelleDifficulte){difficulte = nouvelleDifficulte;}

    public short getVolumeMusique(){return volumeMusique;}
    public void setVolumeMusique(short nouveauVolumeMusique){volumeMusique = nouveauVolumeMusique;}

    public short getVolumeEffets(){return volumeEffets;}
    public void setVolumeEffets(short nouveauVolumeEffets){volumeEffets = nouveauVolumeEffets;}

    public boolean getOrientation(){return orientation;}
    public void setOrientation(boolean nouvelleOrientation){orientation = nouvelleOrientation;}
    //endregion

    public Options()
    {
        difficulte = 2;
        volumeEffets = 5;
        volumeMusique = 5;
        orientation = false;
    }

    public Options(short optionsDifficulte, short optionsVolumeEffets, short optionsVolumeMusique, boolean optionsOrientation)
    {
        difficulte = optionsDifficulte;
        volumeEffets = optionsVolumeEffets;
        volumeMusique = optionsVolumeMusique;
        orientation = optionsOrientation;
    }
}