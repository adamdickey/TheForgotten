package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import forgottenmod.cards.Strike;
import forgottenmod.powers.HandSizeIncrease;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class StrikeHandAction extends AbstractGameAction {
    private final AbstractCard card;

    public StrikeHandAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        int maxHandSize;
        if(player.hasPower(HandSizeIncrease.ID)){
            maxHandSize = 10+player.getPower(HandSizeIncrease.ID).amount;
        } else {
            maxHandSize = 10;
        }
        int strikes = maxHandSize - player.hand.size();
        if(strikes > 0){
            for(int i = 0; i < strikes; i++){
                AbstractCard strike = new Strike();
                if(card.upgraded){
                    strike.upgrade();
                }
                addToBot(new EchoAction(strike, 1));
            }
        }
        this.isDone = true;
    }
}
