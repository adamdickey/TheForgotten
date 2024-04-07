package forgottenmod.actions;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class EchoAction extends AbstractGameAction {
    private final AbstractCard card;
    private final int amount;

    public EchoAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }

    public void update() {
        AbstractCard echo = card.makeStatEquivalentCopy();
        CardModifierManager.addModifier(echo, new ExhaustMod());
        CardModifierManager.addModifier(echo, new EtherealMod());
        addToBot(new MakeTempCardInHandAction(echo, amount, false));
        this.isDone = true;
    }
}
