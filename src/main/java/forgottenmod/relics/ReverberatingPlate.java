package forgottenmod.relics;

import theforgotten.TheForgotten;

import static forgottenmod.BasicMod.makeID;

public class ReverberatingPlate extends BaseRelic {
    private static final String NAME = "Reverberating Plate"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.

    public ReverberatingPlate() {
        super(ID, NAME, TheForgotten.Enums.CARD_COLOR, RARITY, SOUND);
    }
    public String getUpdatedDescription(){
        return this.DESCRIPTIONS[0];
    }
}