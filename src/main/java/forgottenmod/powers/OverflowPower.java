package forgottenmod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Collections;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class OverflowPower extends BasePower implements NonStackablePower {

    public static final String ID = makeID("Overflow");
    public OverflowPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onDrawOrDiscard(){
        ArrayList<AbstractCard> playableCards = new ArrayList<>();
        int handSize = 10;
        if(player.hasPower(HandSizeIncrease.ID)){
            handSize += player.getPower(HandSizeIncrease.ID).amount;
        }
        if(player.hand.size() == handSize){
            for(AbstractCard c : player.hand.group){
                if(c.costForTurn != -2){
                    playableCards.add(c);
                }
            }
            if(playableCards.isEmpty()){
                return;
            }
            Collections.shuffle(playableCards);
            AbstractCard randomCard = playableCards.get(0);
            randomCard.setCostForTurn(0);
            addToBot(new NewQueueCardAction(randomCard, true, false, true));
        }
    }
}