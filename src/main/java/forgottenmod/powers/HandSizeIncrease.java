package forgottenmod.powers;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class HandSizeIncrease extends BasePower {

    public static final String ID = makeID("Hand Size Increase");
    public HandSizeIncrease(int amount) {
        super(ID, PowerType.BUFF, true, player, player, amount, true);
        loadRegion("draw");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void onInitialApplication() {
        super.onInitialApplication();
        BaseMod.MAX_HAND_SIZE += this.amount;
    }
    public void atStartOfTurn(){
        addToBot(new RemoveSpecificPowerAction(player, player, this));
    }

    public void onRemove() {
        super.onRemove();
        BaseMod.MAX_HAND_SIZE -= this.amount;
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        BaseMod.MAX_HAND_SIZE += stackAmount;
    }

    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        BaseMod.MAX_HAND_SIZE -= reduceAmount;
    }

    public void onVictory() {
        super.onVictory();
        BaseMod.MAX_HAND_SIZE -= this.amount;
    }
}