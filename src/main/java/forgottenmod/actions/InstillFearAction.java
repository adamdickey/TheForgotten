package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.powers.Trauma;

public class InstillFearAction extends AbstractGameAction {

    public InstillFearAction(AbstractCreature target) {
       this.target = target;
    }

    public void update() {
        if(target.hasPower(Trauma.ID)){
            if(target.getPower(Trauma.ID).amount >= 30){
                addToBot(new AwakenAction(target));
            }
        }
        this.isDone = true;
    }
}
