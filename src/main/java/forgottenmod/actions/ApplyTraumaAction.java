package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.powers.TraumaPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class ApplyTraumaAction extends AbstractGameAction {

    public ApplyTraumaAction(AbstractCreature target, int amount) {
       this.target = target;
       this.amount = amount;
    }

    public void update() {
        if(target.hasPower(TraumaPower.ID)){
            addToBot(new ApplyPowerAction(target, player, new TraumaPower(amount, target), amount));
            addToBot(new AwakenAction(target));
        } else {
            addToBot(new ApplyPowerAction(target, player, new TraumaPower(amount, target), amount));
        }
        this.isDone = true;
    }
}
