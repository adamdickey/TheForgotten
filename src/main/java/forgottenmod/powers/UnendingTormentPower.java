package forgottenmod.powers;


import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class UnendingTormentPower extends BasePower implements NonStackablePower {

    public static final String ID = makeID("Unending Torment");
    public UnendingTormentPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}