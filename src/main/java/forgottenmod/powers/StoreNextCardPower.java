package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import forgottenmod.actions.StoreOtherCardAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class StoreNextCardPower extends BasePower {

    public static final String ID = makeID("Store Next Card");

    AbstractCard card;
    public StoreNextCardPower(int amount, AbstractCard card) {
        super(ID, PowerType.BUFF, true, player, player, amount, true);
        loadRegion("rebound");
        this.card = card;
    }

    public void updateDescription() {
        if(this.amount == 1){
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
    @Override
    public void atStartOfTurn(){
        addToBot(new RemoveSpecificPowerAction(player, player, this));
    }
    public void onAfterUseCard(AbstractCard cardPlayed, UseCardAction action) {
        if(cardPlayed != card){
            if(!card.exhaust){
                addToBot(new StoreOtherCardAction(cardPlayed, true, player.discardPile));
            }
            if(this.amount == 1){
                addToBot(new RemoveSpecificPowerAction(player, player, this));
            } else {
                addToBot(new ReducePowerAction(player, player, this, 1));
            }

        }
    }
}