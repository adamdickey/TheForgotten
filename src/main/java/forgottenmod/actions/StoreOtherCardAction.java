package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import forgottenmod.powers.StoredPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.*;

public class StoreOtherCardAction extends AbstractGameAction {
    private final AbstractCard card;
    private final boolean handCanBeEmpty;
    private final CardGroup zone;

    public StoreOtherCardAction(AbstractCard card, boolean handCanBeEmpty, CardGroup zone) {
        this.card = card;
        this.handCanBeEmpty = handCanBeEmpty;
        this.zone = zone;
    }

    public void update() {
        if(player.hand.isEmpty() && !player.hasPower(StoredPower.ID) && !card.hasTag(wasStored)){
            addToBot(new ReturnToHandAction(card));
            this.isDone = true;
            return;
        }
        if(player.hasPower(StoredPower.ID)){
            if(player.getPower(StoredPower.ID).amount >= 3){
                createStorageIsFullDialog();
                this.isDone = true;
                return;
            }
        }
        zone.moveToExhaustPile(card);
        addToBot(new ApplyPowerAction(player, player, new StoredPower(player, 1, card), 1));
        this.isDone = true;
    }
    public void createStorageIsFullDialog() {
        float dialogX = player.dialogX;
        float dialogY = player.dialogY;
        AbstractDungeon.effectList.add(new ThoughtBubble(dialogX, dialogY, 3.0F, "#rStorage #ris #rFull!", true));
    }
}
