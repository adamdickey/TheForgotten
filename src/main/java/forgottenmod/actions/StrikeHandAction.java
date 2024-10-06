package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import forgottenmod.cards.Strike;
import forgottenmod.powers.EnlightenmentPower;
import forgottenmod.powers.HandSizeIncrease;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class StrikeHandAction extends AbstractGameAction {

    public StrikeHandAction() {}

    public void update() {
        int maxHandSize = 10;
        if(player.hasPower(HandSizeIncrease.ID)){
            maxHandSize += player.getPower(HandSizeIncrease.ID).amount;
        }
        if(player.hasPower(EnlightenmentPower.ID)){
            maxHandSize += player.getPower(EnlightenmentPower.ID).amount;
        }
        int strikeNum = maxHandSize - player.hand.size();
        if(strikeNum > 0){
            AbstractCard strike = new Strike();
            addToBot(new EchoAction(strike, strikeNum));
        }
        this.isDone = true;
    }
}
