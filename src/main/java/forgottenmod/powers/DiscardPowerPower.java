package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class DiscardPowerPower extends BasePower {

    public static final String ID = makeID("Discard Power");
    public DiscardPowerPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        loadRegion("nirvana");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    int cardsDiscarded = 0;
    boolean endOfTurn = false;
    @Override
    public void onInitialApplication() {
        cardsDiscarded = GameActionManager.totalDiscardedThisTurn;
    }
    public void atStartOfTurn(){
        cardsDiscarded = 0;
        endOfTurn = false;
    }
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        endOfTurn = true;
    }
    public void onDrawOrDiscard() {
        if(GameActionManager.totalDiscardedThisTurn > cardsDiscarded && !endOfTurn){
            for(int i = 0; i < GameActionManager.totalDiscardedThisTurn - cardsDiscarded; i++){
                flash();
                addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            }
        }
        cardsDiscarded = GameActionManager.totalDiscardedThisTurn;
    }
}