package forgottenmod.actions;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import forgottenmod.relics.ReverberatingPlate;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.isEcho;

public class EchoAction extends AbstractGameAction {
    private final AbstractCard card;
    private final int amount;

    public EchoAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
    }


    public void update() {
        if(card.hasTag(isEcho)){
            this.isDone = true;
            return;
        }
        AbstractCard echo = card.makeStatEquivalentCopy();
        CardModifierManager.addModifier(echo, new EchoModifier());
        if(!echo.isEthereal){
            CardModifierManager.addModifier(echo, new EtherealMod());
        }
        if(!echo.exhaust){
            CardModifierManager.addModifier(echo, new ExhaustMod());
        }
        if(player.hasRelic(ReverberatingPlate.ID)){
            addToBot(new MakeTempCardInHandAction(echo, amount+1, false));
        } else {
            addToBot(new MakeTempCardInHandAction(echo, amount, false));
        }
        this.isDone = true;
    }
}
