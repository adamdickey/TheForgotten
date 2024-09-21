package forgottenmod.actions;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import forgottenmod.BasicMod;

import static forgottenmod.BasicMod.isEcho;

public class EchoModifier extends AbstractCardModifier {
    public static String ID = BasicMod.makeID("Echo");

    public String identifier(AbstractCard card) {
        return ID;
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.hasTag(isEcho);
    }

    public boolean removeAtEndOfTurn(AbstractCard card) {
        return false;
    }

    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if(!card.hasTag(isEcho)){
            card.tags.add(isEcho);
        }
        card.applyPowers();
    }
    public void atEndOfTurn(AbstractCard card, CardGroup group) {
        addToBot(new ExhaustSpecificCardAction(card, group));
    }

    public AbstractCardModifier makeCopy() {
        return new EchoModifier();
    }
}
