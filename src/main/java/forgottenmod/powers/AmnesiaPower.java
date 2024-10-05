package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import forgottenmod.cards.Forget;
import forgottenmod.cards.Memory;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class AmnesiaPower extends BasePower {

    public static final String ID = makeID("Amnesia");
    public AmnesiaPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        loadRegion("fasting");
    }

    public void updateDescription() {
        if(this.amount == 1){
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
    @Override
    public void atStartOfTurn(){
        if(player.hasPower(EnlightenmentPower.ID)){
            addToBot(new MakeTempCardInHandAction(new Memory(), 1));
        } else {
            addToBot(new MakeTempCardInHandAction(new Forget()));
        }
    }
}