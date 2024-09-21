package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.powers.DistressPower;

public class InstillFearAction extends AbstractGameAction {

    public InstillFearAction(AbstractCreature target) {
       this.target = target;
    }

    public void update() {
        if(target.hasPower(DistressPower.ID)){
            if(target.getPower(DistressPower.ID).amount >= 30){
                addToBot(new DetonateAction(target));
            }
        }
        this.isDone = true;
    }
}
