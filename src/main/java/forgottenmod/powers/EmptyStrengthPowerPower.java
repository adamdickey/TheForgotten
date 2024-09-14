package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class EmptyStrengthPowerPower extends BasePower {

    public static final String ID = makeID("Empty Strength Power");
    public EmptyStrengthPowerPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    boolean active = false;
    int strengthAmount = 0;

    public void onInitialApplication(){
        checkCards(this.amount);
    }
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power instanceof EmptyStrengthPowerPower){
            checkCards(this.amount + power.amount);
        }
    }
    public void onDrawOrDiscard(){
        checkCards(this.amount);
    }
    public void checkCards(){
        if(player.hand.size() <= 2){
            if(!this.active){
                //flash();
                strengthAmount = this.amount;
                addToBot(new ApplyPowerAction(player, player, new StrengthPower(player, this.amount), this.amount));
                this.active = true;
            }
        } else {
            if(this.active){
                //flash();
                addToBot(new ApplyPowerAction(player, player, new StrengthPower(player, -strengthAmount), -strengthAmount));
                this.active = false;
            }
        }
    }
    public void checkCards(int powerAmount){
        if(player.hand.size() <= 2){
            if(!this.active){
                //flash();
                strengthAmount = powerAmount;
                addToBot(new ApplyPowerAction(player, player, new StrengthPower(player, strengthAmount), strengthAmount));
                this.active = true;
            }
        } else {
            if(this.active){
                //flash();
                addToBot(new ApplyPowerAction(player, player, new StrengthPower(player, -strengthAmount), -strengthAmount));
                this.active = false;
            }
        }
    }
}