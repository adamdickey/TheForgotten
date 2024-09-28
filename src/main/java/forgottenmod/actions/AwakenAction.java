package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.powers.Trauma;
import forgottenmod.powers.UnendingTormentPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class AwakenAction extends AbstractGameAction {

    public AwakenAction(AbstractCreature target) {
       this.target = target;
    }

    public void update() {
        if(target.hasPower(Trauma.ID)){
            addToBot(new DamageAction(target, new DamageInfo(target, target.getPower(Trauma.ID).amount, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
            if(!player.hasPower(UnendingTormentPower.ID)){
                addToBot(new RemoveSpecificPowerAction(target, player, Trauma.ID));
            }
        }
        this.isDone = true;
    }
}
