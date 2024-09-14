package forgottenmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.powers.TrackerPower;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DamagePerCard extends BaseCard {
    public static final String ID = makeID("Damage Per Card"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public DamagePerCard() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        int baseDamage = 2;
        int UPG_Damage = 0;
        int baseMagicNumber2 = 2;
        int UPG_Number = 1;
        setDamage(baseDamage, UPG_Damage);
        setCustomVar("Magic", baseMagicNumber2, UPG_Number);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        if(player.hasPower(TrackerPower.ID)){
            baseMagicNumber = player.getPower(TrackerPower.ID).amount*customVar("Magic");
        } else {
            baseMagicNumber = 5*customVar("Magic");
        }
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
    public void calculateCardDamage(AbstractMonster mo) {
        if(player.hasPower(TrackerPower.ID)){
            baseMagicNumber = player.getPower(TrackerPower.ID).amount*customVar("Magic");;
        } else {
            baseMagicNumber = 5*customVar("Magic");;
        }
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
}
