package forgottenmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.actions.AwakenAction;
import forgottenmod.powers.Trauma;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager;

public class Torture extends BaseCard {
    public static final String ID = makeID("Torture"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public Torture() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        int baseMagicNumber = 5;
        int UPG_Number = 2;
        setMagic(baseMagicNumber, UPG_Number);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : actionManager.cardsPlayedThisTurn) {
            if (c.type == AbstractCard.CardType.ATTACK)
                count++;
        }
        if(count > 0){
            if(m.hasPower(Trauma.ID)){
                addToBot(new ApplyPowerAction(m, p, new Trauma(count*magicNumber, m), count*magicNumber));
                addToBot(new AwakenAction(m));
            } else {
                addToBot(new ApplyPowerAction(m, p, new Trauma(count*magicNumber, m), count*magicNumber));
            }
        }
    }
    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : actionManager.cardsPlayedThisTurn) {
            if (c.type == AbstractCard.CardType.ATTACK)
                count++;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
}
