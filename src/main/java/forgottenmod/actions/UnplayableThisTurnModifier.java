package forgottenmod.actions;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import forgottenmod.BasicMod;

public class UnplayableThisTurnModifier extends AbstractCardModifier {
    public static String ID = BasicMod.makeID("Unplayable");

    public String identifier(AbstractCard card) {
        return ID;
    }

    public boolean shouldApply(AbstractCard card) {
        return !(card.costForTurn == -2);
    }

    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }

    public void onInitialApplication(AbstractCard card) {}

    public boolean canPlayCard(AbstractCard card) {
        return false;
    }

    public AbstractCardModifier makeCopy() {
        return new UnplayableThisTurnModifier();
    }
}
