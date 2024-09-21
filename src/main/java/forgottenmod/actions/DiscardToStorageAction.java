package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import forgottenmod.powers.StoredPower;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DiscardToStorageAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("DiscardToStorageAction")).TEXT;

    public DiscardToStorageAction(int amount) {
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if(player.hasPower(StoredPower.ID)){
            if(player.getPower(StoredPower.ID).amount == 3){
                float dialogX = player.dialogX;
                float dialogY = player.dialogY;
                AbstractDungeon.effectList.add(new ThoughtBubble(dialogX, dialogY, 3.0F, "#rStorage #ris #rFull!", true));
                this.isDone = true;
                return;
            }
        }
        if (this.duration == this.startDuration) {
            if (player.discardPile.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (player.discardPile.size() <= this.amount) {
                ArrayList<AbstractCard> cardsToMove = new ArrayList<>(player.discardPile.group);
                for (AbstractCard c : cardsToMove) {
                    addToBot(new StoreOtherCardAction(c, true, player.discardPile));
                }
                this.isDone = true;
                return;
            }
            if (this.amount == 1) {
                AbstractDungeon.gridSelectScreen.open(player.discardPile, this.amount, TEXT[0], false);
            } else {
                AbstractDungeon.gridSelectScreen.open(player.discardPile, this.amount, TEXT[1] + this.amount + TEXT[2], false);
            }
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    addToBot(new StoreOtherCardAction(c, true, player.discardPile));
                    player.discardPile.removeCard(c);
                c.lighten(false);
                c.unhover();
                c.applyPowers();
            }
            for (AbstractCard c : player.discardPile.group) {
                c.unhover();
                c.target_x = CardGroup.DISCARD_PILE_X;
                c.target_y = 0.0F;
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        tickDuration();
    }

}
