package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class InsanityPower extends BasePower {

    public static final String ID = makeID("Insanity");
    public InsanityPower(int amount) {
        super(ID, PowerType.DEBUFF, false, player, player, amount, true);
        loadRegion("wraithForm");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        flash();
        addToBot(new DamageAction(player, new DamageInfo(player, player.hand.size()*this.amount, DamageInfo.DamageType.THORNS)));
    }
}