package forgottenmod.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class CreateCheapPowerPower extends BasePower implements OnCreateCardInterface {

    public static final String ID = makeID("Create Cheap Power");
    public CreateCheapPowerPower(int amount) {
        super(ID, PowerType.BUFF, false, player, player, amount, true);
        loadRegion("afterImage");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        if(abstractCard.costForTurn > this.amount){
            abstractCard.costForTurn -= this.amount;
            abstractCard.isCostModified = true;
        } else if(abstractCard.costForTurn > 0){
            abstractCard.costForTurn = 0;
            abstractCard.isCostModified = true;
        }
    }
}