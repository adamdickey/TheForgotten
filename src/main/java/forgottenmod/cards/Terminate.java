package forgottenmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.actions.StorageAction;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import static forgottenmod.BasicMod.selfStoring;
import static forgottenmod.BasicMod.wasStored;

public class Terminate extends BaseCard {
    public static final String ID = makeID("Terminate"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public Terminate() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        int baseDamage = 20;
        int UPG_Damage = 5;
        setDamage(baseDamage, UPG_Damage);
        this.tags.add(selfStoring);
        this.tags.remove(wasStored);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hand.size() > 1 && !this.purgeOnUse){
            addToBot(new StorageAction(this));
        } else {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        }

    }
}
