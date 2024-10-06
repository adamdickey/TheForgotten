package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import forgottenmod.powers.TraumaPower;
import forgottenmod.powers.UnendingTormentPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class AwakenAction extends AbstractGameAction {

    public AwakenAction(AbstractCreature target) {
       this.target = target;
    }

    public void update() {
        if(target.hasPower(TraumaPower.ID) && !target.hasPower(ArtifactPower.POWER_ID)){
            addToBot(new DamageAction(target, new DamageInfo(target, target.getPower(TraumaPower.ID).amount, DamageInfo.DamageType.THORNS), AttackEffect.FIRE));
            if(!player.hasPower(UnendingTormentPower.ID)){
                addToBot(new RemoveSpecificPowerAction(target, player, TraumaPower.ID));
            }
        }
        this.isDone = true;
    }
}
