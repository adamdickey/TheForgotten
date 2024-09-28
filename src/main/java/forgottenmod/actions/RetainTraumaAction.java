package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.powers.Trauma;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class RetainTraumaAction extends AbstractGameAction {

    AbstractCreature m;
    AbstractCard c;

    public RetainTraumaAction(int amount, AbstractCreature m, AbstractCard c) {
        this.amount = amount;
        this.m = m;
        this.c = c;
    }

    public void update() {
        if(m.hasPower(Trauma.ID)){
            addToBot(new ApplyPowerAction(m, player, new Trauma(amount, m), amount));
            addToBot(new AwakenAction(m));
        } else {
            addToBot(new ApplyPowerAction(m, player, new Trauma(amount, m), amount));
            if(!c.purgeOnUse){
                addToBot(new DiscardToHandAction(c));
            }
        }
        this.isDone = true;
    }
}
