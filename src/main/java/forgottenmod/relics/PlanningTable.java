package forgottenmod.relics;

import basemod.BaseMod;
import theforgotten.TheForgotten;

import static forgottenmod.BasicMod.makeID;

public class PlanningTable extends BaseRelic {
    private static final String NAME = "Planning Table"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    public PlanningTable() {
        super(ID, NAME, TheForgotten.Enums.CARD_COLOR, RARITY, SOUND);
    }
    public String getUpdatedDescription(){
        return this.DESCRIPTIONS[0];
    }
    public void onEquip(){
        super.onEquip();
        BaseMod.MAX_HAND_SIZE += 2;
    }
    public void onUnequip(){
        super.onUnequip();
        BaseMod.MAX_HAND_SIZE -= 2;
    }
}