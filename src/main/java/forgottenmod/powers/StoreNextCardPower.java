package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.actions.StoreOtherCardAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class StoreNextCardPower extends BasePower {

    public static final String ID = makeID("Store Next Card");
    public StoreNextCardPower(int amount) {
        super(ID, PowerType.BUFF, true, player, player, amount, true);
        loadRegion("rebound");
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
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(!card.exhaust){
            addToBot(new StoreOtherCardAction(card, true, player.discardPile));
        }
        addToBot(new RemoveSpecificPowerAction(player, player, this));
    }
}