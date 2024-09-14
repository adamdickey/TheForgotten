package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DrawUntilAttackAction extends AbstractGameAction {

    private final boolean isUpgraded;
    public DrawUntilAttackAction(boolean isUpgraded) {
        this.isUpgraded = isUpgraded;
    }

    public void update() {
        if(DrawCardAction.drawnCards.get(0).type != AbstractCard.CardType.ATTACK){
            addToBot(new DrawCardAction(1, new DrawUntilAttackAction(this.isUpgraded)));
        } else if(!this.isUpgraded){
            addToBot(new StoreOtherCardAction(DrawCardAction.drawnCards.get(0), true, player.hand));
        }
        this.isDone = true;
    }
}
