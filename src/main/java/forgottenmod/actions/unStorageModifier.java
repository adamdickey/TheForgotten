package forgottenmod.actions;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import forgottenmod.BasicMod;

import static forgottenmod.BasicMod.isEcho;
import static forgottenmod.BasicMod.wasStored;

public class unStorageModifier extends AbstractCardModifier {
    public static String ID = BasicMod.makeID("Storage");

    public String identifier(AbstractCard card) {
        return ID;
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.hasTag(wasStored);
    }

    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if(!card.hasTag(wasStored)){
            card.tags.add(wasStored);
        }
        card.applyPowers();
    }
    public void atEndOfTurn(AbstractCard card, CardGroup group) {
        if(card.hasTag(wasStored)){
            card.tags.remove(wasStored);
        }
    }
    public void onDrawn(AbstractCard card) {
        if(card.hasTag(wasStored)){
            card.tags.remove(wasStored);
        }
    }

    public AbstractCardModifier makeCopy() {
        return new unStorageModifier();
    }
}
