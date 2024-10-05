package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static forgottenmod.BasicMod.makeID;

public class DrawReduction2Power extends BasePower {

    public static final String ID = makeID("Draw Reduction");
    public DrawReduction2Power(AbstractPlayer player, int amount) {
        super(ID, PowerType.DEBUFF, true, player, player, amount, true);
        loadRegion("lessdraw");
    }

    public void onInitialApplication() {
        AbstractDungeon.player.gameHandSize -= this.amount;
    }
    public void onRemove(){
        AbstractDungeon.player.gameHandSize += this.amount;
    }

    public void atStartOfTurnPostDraw(){
        if(AbstractDungeon.player.hasPower(DrawReduction2Power.ID)){
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, DrawReduction2Power.ID));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}