package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class XDiscardAction extends AbstractGameAction {
    private final boolean freeToPlayOnce;

    private final AbstractPlayer p;

    private final int energyOnUse;

    private final boolean upgraded;
    int cardsDiscarded = 0;

    public XDiscardAction(AbstractPlayer p, int energyOnUse, boolean upgraded, boolean freeToPlayOnce) {
        this.p = p;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
        this.upgraded = upgraded;
        this.freeToPlayOnce = freeToPlayOnce;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (this.upgraded)
            effect++;
        if (effect > 0) {

            addToBot(new HandSelectAction(effect, c -> true, list -> {
            },list -> {
                for (AbstractCard c : list) {
                    AbstractDungeon.handCardSelectScreen.selectedCards.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                    cardsDiscarded++;
                }
                if(cardsDiscarded > 0){
                    if(this.upgraded){
                        addToBot(new GainEnergyAction(2));
                        addToBot(new ApplyPowerAction(player, player, new DrawCardNextTurnPower(player, cardsDiscarded)));
                    } else {
                        addToBot(new GainEnergyAction(1));
                        addToBot(new ApplyPowerAction(player, player, new DrawCardNextTurnPower(player, cardsDiscarded)));
                    }
                }
            },"Discard", false, false, false));
            if (!this.freeToPlayOnce)
                this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
