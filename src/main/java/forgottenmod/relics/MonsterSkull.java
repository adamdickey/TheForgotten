package forgottenmod.relics;

import theforgotten.TheForgotten;

import static forgottenmod.BasicMod.makeID;

public class MonsterSkull extends BaseRelic {
    private static final String NAME = "Monster Skull"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    public MonsterSkull() {
        super(ID, NAME, TheForgotten.Enums.CARD_COLOR, RARITY, SOUND);
    }
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}