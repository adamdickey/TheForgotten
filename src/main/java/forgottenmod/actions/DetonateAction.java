package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.powers.DistressPower;

public class DetonateAction extends AbstractGameAction {

    public DetonateAction(AbstractCreature target) {
       this.target = target;
    }

    public void update() {
        if(target.hasPower(DistressPower.ID)){
            addToBot(new DamageAction(target, new DamageInfo(target, target.getPower(DistressPower.ID).amount, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
        }
        this.isDone = true;
    }
}
