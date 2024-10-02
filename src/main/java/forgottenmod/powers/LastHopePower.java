package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import forgottenmod.cards.StoreStrength;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class LastHopePower extends BasePower {

    public static final String ID = makeID("Last Hope");

    public LastHopePower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        this.amount = amount;
    }
    boolean drawn = false;

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void onAfterUseCard(AbstractCard card, UseCardAction action){
        drawn = false;
        if(card.type == AbstractCard.CardType.POWER){
            drawCard();
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
                !AbstractDungeon.actionManager.turnHasEnded){
            flash();
            addToBot(new GainBlockAction(player, this.amount));
        }

    }

    public void drawCard() {
        if (AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.actionManager.turnHasEnded){
            flash();
            addToBot(new GainBlockAction(player, this.amount));
        }
    }
}