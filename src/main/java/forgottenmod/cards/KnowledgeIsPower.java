package forgottenmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class KnowledgeIsPower extends BaseCard {
    public static final String ID = makeID("Knowledge is Power"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.


    public KnowledgeIsPower() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        int baseMagicNumber = 3;
        int UPG_Number = -1;
        setCustomVar("Magic", baseMagicNumber, UPG_Number);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseMagicNumber = (int) Math.floor((double) player.hand.size() /customVar("Magic"));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.baseMagicNumber)));
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        this.baseMagicNumber = (int) Math.floor((double) player.hand.size() /customVar("Magic"));
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
}
