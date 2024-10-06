package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.powers.TraumaPower;

public class InstillFearAction extends AbstractGameAction {

    public InstillFearAction(AbstractCreature target) {
       this.target = target;
    }

    public void update() {
        if(target.hasPower(TraumaPower.ID)){
            if(target.getPower(TraumaPower.ID).amount >= 30){
                addToBot(new AwakenAction(target));
            }
        }
        this.isDone = true;
    }
}
