package forgottenmod.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import static forgottenmod.BasicMod.makeID;

public class CardsCreatedThisCombat extends BasePower implements InvisiblePower, OnCreateCardInterface {

    public static final String ID = makeID("Cards Created This Combat");
    public CardsCreatedThisCombat(AbstractPlayer player, int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, false);
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        this.amount++;
    }
}