package forgottenmod.powers;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.localization.LocalizedStrings;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class OmnipotentPower extends BasePower {

    public static final String ID = makeID("Omnipotent");

    public OmnipotentPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        this.amount = amount;
        loadRegion("deva2");
    }

    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.DESCRIPTIONS[0]);
        for (int i = 0; i < this.amount; i++)
            sb.append("[E] ");
        sb.append(LocalizedStrings.PERIOD);
        this.description = sb.toString();
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void atStartOfTurn(){
        addToBot(new GainEnergyAction(this.amount));
    }
}