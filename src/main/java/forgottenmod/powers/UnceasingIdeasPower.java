package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import forgottenmod.cards.StoreStrength;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.*;

public class UnceasingIdeasPower extends BasePower {

    public static final String ID = makeID("Unceasing Ideas");

    public UnceasingIdeasPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        this.amount = amount;
        loadRegion("hymn");
    }
    boolean drawn = false;

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }
    public void onUseCard(AbstractCard card, UseCardAction action){
        drawn = false;
        if(card.hasTag(selfStoring) && !card.hasTag(wasStored)){
            drawn = true;
        }
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action){
        if(card.type == AbstractCard.CardType.POWER){
            drawCard();
            drawn = true;
        }
    }

    public void onDrawOrDiscard(){
        if(!drawn){
            drawCard();
            drawn = true;
        }
    }
    public void onExhaust(AbstractCard card){
        if (card instanceof StoreStrength && player.hand.size() == 1 &&
                !actionManager.turnHasEnded && !AbstractDungeon.player.hasPower("No Draw"))
            if ((!AbstractDungeon.player.discardPile.isEmpty() || !AbstractDungeon.player.drawPile.isEmpty())) {
                flash();
                addToBot(new DrawCardAction(AbstractDungeon.player, this.amount));
            }
    }

    public void drawCard() {
        if (player.hand.isEmpty() && !actionManager.turnHasEnded && !player.hasPower("No Draw"))
            if ((!player.discardPile.isEmpty() || !player.drawPile.isEmpty()) && !player.hasPower(StoredPower.ID)) {
                flash();
                addToBot(new DrawCardAction(AbstractDungeon.player, this.amount));
            }
    }
}