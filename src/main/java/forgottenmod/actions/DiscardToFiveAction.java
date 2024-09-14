package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DiscardToFiveAction extends AbstractGameAction {

    ArrayList<AbstractCard> notScraped = new ArrayList<>();
    public DiscardToFiveAction() {
    }

    public void update() {
        int cardsToDiscard = player.hand.size() - 5;
        if(cardsToDiscard > 0){
            addToBot(new HandSelectAction(cardsToDiscard, c -> true, list -> {
            },list -> {
                for (AbstractCard c : list) {
                    AbstractDungeon.handCardSelectScreen.selectedCards.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
            },"Discard", false, false, false));
        }
        this.isDone = true;
    }
}
