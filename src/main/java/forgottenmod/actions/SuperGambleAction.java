package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class SuperGambleAction extends AbstractGameAction {
    private final int amount;

    public SuperGambleAction(int amount) {
        this.amount = amount;
    }

    public void update() {
        int theSize = player.hand.size();
        addToBot(new DiscardAction(player, player, theSize, false));
        addToBot(new DrawCardAction(theSize+amount));
        this.isDone = true;
    }
}
