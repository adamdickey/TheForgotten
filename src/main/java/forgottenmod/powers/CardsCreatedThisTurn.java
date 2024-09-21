package forgottenmod.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.DexterityPower;
import forgottenmod.cards.ExhaustDexterity;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.isEcho;
import static forgottenmod.BasicMod.makeID;

public class CardsCreatedThisTurn extends BasePower implements InvisiblePower, OnCreateCardInterface {

    public static final String ID = makeID("Cards Created This Turn");
    public CardsCreatedThisTurn(AbstractPlayer player, int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, false);
    }

    public void atStartOfTurn() {
        this.amount = 0;
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        this.amount++;
    }
}