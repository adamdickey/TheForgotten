package forgottenmod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import forgottenmod.actions.StayingPowerAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class StayingPowerPower extends BasePower implements InvisiblePower {

    public static final String ID = makeID("Staying Power");
    public StayingPowerPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        this.priority = 25;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void atEndOfTurn(boolean isPlayer){
        addToBot(new StayingPowerAction(this.amount));

    }
}