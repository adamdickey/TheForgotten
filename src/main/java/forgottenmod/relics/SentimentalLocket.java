package forgottenmod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import forgottenmod.BasicMod;
import forgottenmod.cards.Memory;
import theforgotten.TheForgotten;

public class SentimentalLocket extends BaseRelic {
    private static final String NAME = "Sentimental Locket"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = BasicMod.makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public SentimentalLocket() {
        super(ID, NAME, TheForgotten.Enums.CARD_COLOR, RARITY, SOUND);
    }
    @Override
    public void atBattleStartPreDraw() {
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new MakeTempCardInHandAction(new Memory(), 1, false));
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}