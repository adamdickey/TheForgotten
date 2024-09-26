package forgottenmod.powers;

import forgottenmod.actions.StayingPowerAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class StayingPowerPower extends BasePower {

    public static final String ID = makeID("Staying Power");
    public StayingPowerPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void atEndOfTurn(boolean isPlayer){
        flash();
        addToBot(new StayingPowerAction(this.amount));

    }
}