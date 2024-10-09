package forgottenmod.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import forgottenmod.cards.Strike;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class StrikeHandAction extends AbstractGameAction {

    public StrikeHandAction() {}

    public void update() {
        int strikeNum = BaseMod.MAX_HAND_SIZE - player.hand.size();
        if(strikeNum > 0){
            AbstractCard strike = new Strike();
            addToBot(new EchoAction(strike, strikeNum));
        }
        this.isDone = true;
    }
}
