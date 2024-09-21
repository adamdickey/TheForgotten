package forgottenmod.actions;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import forgottenmod.cards.DoubleStrike;
import forgottenmod.cards.DualClaw;

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
        if(card instanceof DualClaw){
            echo.baseDamage = card.baseDamage;
        }
        addToBot(new MakeTempCardInHandAction(echo, amount, false));
        this.isDone = true;
    }
}
