package forgottenmod.powers;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.DexterityPower;
import forgottenmod.cards.ExhaustDexterity;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.*;

public class TrackerPower extends BasePower implements InvisiblePower {

    public static final String ID = makeID("Tracker");
    public TrackerPower(AbstractPlayer player, int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
    }

    public void atStartOfTurn() {
        this.amount = 0;
    }
    public void onCardDraw(AbstractCard card) {
        this.amount++;
        if(card.hasTag(wasStored)){
            card.setCostForTurn(0);
        }
    }
    public void onDrawOrDiscard(){
        if(player.hand.size() == BaseMod.MAX_HAND_SIZE){
            for(AbstractCard c : player.hand.group){
                if(c instanceof ExhaustDexterity){
                    addToBot(new ApplyPowerAction(player, player, new DexterityPower(player, c.magicNumber)));
                    addToBot(new ExhaustSpecificCardAction(c, player.hand));
                }
            }
        }
    }
}