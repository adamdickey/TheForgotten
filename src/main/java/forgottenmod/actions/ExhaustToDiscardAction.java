package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class ExhaustToDiscardAction extends AbstractGameAction {
    private final AbstractCard card;

    public ExhaustToDiscardAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        if(AbstractDungeon.player.exhaustPile.contains(this.card)){
            player.discardPile.addToTop(card);
            this.card.stopGlowing();
            this.card.unhover();
            this.card.unfadeOut();
            this.card.setAngle(0.0F, true);
            this.card.lighten(false);
            this.card.drawScale = 0.12F;
            this.card.targetDrawScale = 0.75F;
            this.card.applyPowers();
            AbstractDungeon.player.exhaustPile.removeCard(this.card);
        }
        this.isDone = true;
    }
}
