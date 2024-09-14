package forgottenmod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import forgottenmod.cards.DiscardHandAttack;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class UnceasingTopPower extends BasePower {

    public static final String ID = makeID("Unceasing Top");

    public UnceasingTopPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        this.amount = amount;
        loadRegion("hymn");
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if ((player.hand.isEmpty() || card instanceof DiscardHandAttack) && !(player.hasPower(StoredPower.ID))) {
            flash();
            addToBot(new DrawCardAction(AbstractDungeon.player, this.amount));
        }
    }

    public void onDrawOrDiscard() {
        //drawCard();
    }

    public void drawCard() {
        if (AbstractDungeon.actionManager.actions.isEmpty() && AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.actionManager.turnHasEnded &&
                !AbstractDungeon.player.hasPower("No Draw"))
            if ((!AbstractDungeon.player.discardPile.isEmpty() || !AbstractDungeon.player.drawPile.isEmpty())
                    && !player.hasPower(StoredPower.ID)) {
                flash();
                addToBot(new DrawCardAction(AbstractDungeon.player, this.amount));
            }
    }
}