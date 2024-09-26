package forgottenmod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class RingOfKeys extends BaseRelic {
    private static final String NAME = "Ring of Keys"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.SHOP; //The relic's rarity.
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.FLAT; //The sound played when the relic is clicked.

    public RingOfKeys() {
        super(ID, NAME, TheForgotten.Enums.CARD_COLOR, RARITY, SOUND);
    }
    public void atBattleStart() {
        updateCounter();
        if (counter > 0) {
            addToBot(new RelicAboveCreatureAction(player, this));
            addToBot(new DrawCardAction(player, counter));
        }
    }

    @Override
    public void onEquip() {
        updateCounter();
    }
    @Override
    public void onMasterDeckChange() {
        updateCounter();
    }
    void updateCounter() {
        counter = 0;
        for(AbstractCard c: player.masterDeck.group){
            if(c.isInnate || c.inBottleFlame || c.inBottleLightning || c.inBottleTornado){
                counter++;
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
//Old Ring of Keys effect:
//    public String getUpdatedDescription() {
//        return this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1] + 10 + this.DESCRIPTIONS[2];
//    }
//    public void atBattleStart(){
//        flash();
//        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        int cardDraw = (int) Math.floor((double) AbstractDungeon.player.drawPile.size() /10);
//        addToBot(new DrawCardAction(AbstractDungeon.player, cardDraw));
//    }
//}