package forgottenmod.cards;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.actions.HandSelectAction;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

public class Unburden extends BaseCard {
    public static final String ID = makeID("Unburden"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public Unburden() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
    }
    int cardsDiscarded = 0;

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        cardsDiscarded = 0;
        addToBot(new HandSelectAction(3, c -> true, list -> {
        },list -> {
            for (AbstractCard c : list) {
                AbstractDungeon.handCardSelectScreen.selectedCards.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
                cardsDiscarded++;
            }
            if(cardsDiscarded > 0){
                if(this.upgraded){
                    addToBot(new GainEnergyAction(cardsDiscarded));
                } else {
                    addToBot(new GainEnergyAction(cardsDiscarded-1));
                }
            }
        },"Discard", false, true, true));

    }
}
