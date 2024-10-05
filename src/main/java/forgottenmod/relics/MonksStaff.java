package forgottenmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import forgottenmod.powers.StayingPowerPower;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class MonksStaff extends BaseRelic {
    private static final String NAME = "Monk's Staff"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    public MonksStaff() {
        super(ID, NAME, TheForgotten.Enums.CARD_COLOR, RARITY, SOUND);
    }
    public String getUpdatedDescription(){
        return this.DESCRIPTIONS[0];
    }
    public void atBattleStart() {
        addToBot(new ApplyPowerAction(player, player, new StayingPowerPower(1)));
    }
}