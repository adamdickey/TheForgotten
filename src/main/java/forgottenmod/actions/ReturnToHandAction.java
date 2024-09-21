package forgottenmod.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import forgottenmod.cards.DualClaw;

public class ReturnToHandAction extends AbstractGameAction {
    private final AbstractCard card;

    public ReturnToHandAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        card.setCostForTurn(0);
        CardModifierManager.addModifier(card, new unStorageModifier());
        this.isDone = true;
    }
}
