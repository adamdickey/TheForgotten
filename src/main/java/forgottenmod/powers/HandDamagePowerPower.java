package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class HandDamagePowerPower extends BasePower{

    public static final String ID = makeID("Hand Damage Power");
    public HandDamagePowerPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        loadRegion("combust");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer){
        int cardsInHand = player.hand.size();
        if(cardsInHand > 0){
            addToBot(new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(this.amount*cardsInHand, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            }
        }
}