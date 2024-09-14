package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class ExtraDrawPowerPower extends BasePower {

    public static final String ID = makeID("Extra Draw");
    public ExtraDrawPowerPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        loadRegion("rushdown");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    @Override
    public void atStartOfTurn(){
        flash();
        addToBot(new DrawCardAction(this.amount));
    }
}