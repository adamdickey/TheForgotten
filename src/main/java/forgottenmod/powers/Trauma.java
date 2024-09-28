package forgottenmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.relics.MonsterSkull;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class Trauma extends BasePower {

    public static final String ID = makeID("Trauma");
    public Trauma(int amount, AbstractCreature target) {
        super(ID, PowerType.DEBUFF, true, target, player, amount, true);
    }

    public void updateDescription() {
        if(player.hasRelic(MonsterSkull.ID)){
            this.description = DESCRIPTIONS[0] + 2 + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + 3 + DESCRIPTIONS[1];
        }
    }
    @Override
    public void atStartOfTurn(){
        int traumaIncrease;
        if(player.hasRelic(MonsterSkull.ID)){
            traumaIncrease = (int) Math.floor((double) this.amount / 2);
        } else {
            traumaIncrease = (int) Math.floor((double) this.amount / 3);
        }
        if(traumaIncrease > 0){
            this.amount += traumaIncrease;
        }
    }
}