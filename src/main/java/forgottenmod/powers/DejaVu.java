package forgottenmod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import forgottenmod.actions.EchoAction;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class DejaVu extends BasePower {

    public static final String ID = makeID("Deja Vu");
    public DejaVu(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        loadRegion("infiniteBlades");
    }

    public void updateDescription() {
        if(this.amount == 1){
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
    int echoes = 0;
    @Override
    public void atStartOfTurn(){
        echoes = 0;
    }
    public void onCardDraw(AbstractCard card){
        if(echoes < this.amount){
            echoes++;
            addToBot(new EchoAction(card, 1));
        }
    }
}