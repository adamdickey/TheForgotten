package forgottenmod.cards;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.powers.CardsCreatedThisCombat;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DamagePerCreated extends BaseCard implements OnCreateCardInterface {
    public static final String ID = makeID("Damage Per Created"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    int cardsCreatedThisCombat = 0;
    public DamagePerCreated() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        int baseDamage = 8;
        int UPG_Damage = 4;
        int Magic = 2;
        setDamage(baseDamage, UPG_Damage);
        setCustomVar("Magic", Magic);
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
    }
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        if(player.hasPower(CardsCreatedThisCombat.ID)){
            cardsCreatedThisCombat = player.getPower(CardsCreatedThisCombat.ID).amount;
        }
        this.baseMagicNumber = cardsCreatedThisCombat * customVar("Magic");
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = cardsCreatedThisCombat * customVar("Magic");
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        calculateCardDamage(m);
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        applyPowers();
    }
}
