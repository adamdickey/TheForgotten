package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class StayingPowerAction extends AbstractGameAction {

    public StayingPowerAction(int amount) {
        this.amount = amount;
    }

    public void update() {
        for(AbstractCard c : player.hand.group){
            if(c.selfRetain || c.retain){
                c.baseDamage += this.amount;
                c.baseBlock += this.amount;
            }
        }
        this.isDone = true;
    }
}
