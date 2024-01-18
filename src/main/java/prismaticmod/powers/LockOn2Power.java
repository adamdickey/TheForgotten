package prismaticmod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static prismaticmod.BasicMod.makeID;

public class LockOn2Power extends BasePower implements OnLoseBlockPower{

    public static final String ID = makeID("Lock-On+");
    private static final int MULTI_STR = 50;

    public LockOn2Power(AbstractCreature owner, int amount) {
        super(ID, PowerType.DEBUFF, true, owner, player, amount, true);
        loadRegion("lockon");
    }
    @Override
    public int onLoseBlock(DamageInfo info, int damageAmount){
        if(info.type != DamageInfo.DamageType.NORMAL){
            info.isModified = true;
            return this.onAttackedToChangeDamage(info, (int) (damageAmount*1.5));
        }
        return damageAmount;
    }
    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(info.type != DamageInfo.DamageType.NORMAL && !info.isModified){
            return (int)(damageAmount*1.5);
        }
        return damageAmount;
    }

    public void atEndOfRound() {
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, LockOn2Power.ID));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, LockOn2Power.ID, 1));
        }
    }

    public void updateDescription() {
        if (this.owner != null)
            if (this.amount == 1) {
                this.description = DESCRIPTIONS[0] + MULTI_STR + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
            } else {
                this.description = DESCRIPTIONS[0] + MULTI_STR + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
            }
    }


}