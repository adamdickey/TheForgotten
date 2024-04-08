package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class MemoryPalacePower extends BasePower implements OnCreateCardInterface {

    public static final String ID = makeID("Memory Palace");
    public MemoryPalacePower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    boolean playerTurn = true;

    public void atStartOfTurn(){
        playerTurn = true;
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer){
        playerTurn = false;
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        if(playerTurn || abstractCard.type != AbstractCard.CardType.STATUS){
            flash();
            addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
        }
    }
}