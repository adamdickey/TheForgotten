package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class HandCopyingSkillAction extends AbstractGameAction {

    ArrayList<AbstractCard> cardsInHand;
    AbstractCard card;

    public HandCopyingSkillAction(ArrayList<AbstractCard> cardsInHand, AbstractCard card) {
        this.cardsInHand = cardsInHand;
        this.card = card;
    }

    public void update() {
        cardsInHand.addAll(player.hand.group);
        int theSize = player.hand.size();
        addToBot(new DiscardAction(player, player, theSize, false));
        for(AbstractCard c : cardsInHand){
            if(c != this.card){
                addToBot(new MakeTempCardInHandAction(c, false));
            }
        }
        this.isDone = true;
    }
}
