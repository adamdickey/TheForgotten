package forgottenmod.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class LockingDrawAction extends AbstractGameAction {

    public LockingDrawAction(int amount) {
        this.amount = amount;
    }

    public void update() {
        addToBot(new HandSelectAction(amount, c -> DrawCardAction.drawnCards.contains(c), cards -> {
            ArrayList<AbstractCard> notChosen = new ArrayList<>(DrawCardAction.drawnCards);
            notChosen.removeAll(cards);
            for (AbstractCard c : notChosen){
                CardModifierManager.addModifier(c, new UnplayableThisTurnModifier());
            }
        },null,"Play this Turn", false, false, false));
        this.isDone = true;
    }
}
