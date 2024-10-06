package forgottenmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import forgottenmod.relics.MonsterSkull;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class TraumaPower extends BasePower implements HealthBarRenderPower {

    public static final String ID = makeID("Trauma");
    public TraumaPower(int amount, AbstractCreature target) {
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
            flash();
            this.amount += traumaIncrease;
        }
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return Color.valueOf("CB89EC");
    }
}