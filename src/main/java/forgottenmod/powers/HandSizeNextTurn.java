package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class HandSizeNextTurn extends BasePower {

    int idOffset;
    public static final String ID = makeID("Next Turn Hand Size");
    public HandSizeNextTurn(int amount) {
        super(ID, PowerType.BUFF, true, player, player, amount, true);
    }

    public void updateDescription() {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void atStartOfTurn(){
        addToBot(new ApplyPowerAction(player, player, new HandSizeIncrease(amount), amount));
        addToBot(new RemoveSpecificPowerAction(player, player, this));
    }
}