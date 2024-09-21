package forgottenmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class DistressPower extends BasePower {

    public static final String ID = makeID("Distress");
    public DistressPower(int amount, AbstractCreature target) {
        super(ID, PowerType.DEBUFF, true, target, player, amount, true);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
    @Override
    public void atStartOfTurn(){
        int distressIncrease = (int) Math.floor((double) this.amount / 5);
        if(distressIncrease > 0){
            this.amount += distressIncrease;
        }
    }
}