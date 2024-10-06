package forgottenmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.powers.TraumaPower;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class ScaryMask extends BaseRelic {
    private static final String NAME = "Scary Mask"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    public ScaryMask() {
        super(ID, NAME, TheForgotten.Enums.CARD_COLOR, RARITY, SOUND);
    }
    public void atBattleStart() {
        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            addToBot(new ApplyPowerAction(m, player, new TraumaPower(5, m), 5));
        }
    }
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}