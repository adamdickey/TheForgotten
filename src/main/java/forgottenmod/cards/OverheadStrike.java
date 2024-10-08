package forgottenmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;
import com.megacrit.cardcrawl.powers.RetainCardPower;
import com.megacrit.cardcrawl.relics.RunicPyramid;
import forgottenmod.powers.RetainThisTurn;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.isEcho;


public class OverheadStrike extends BaseCard {
    public static final String ID = makeID("Overhead Strike"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a base game character color.
            AbstractCard.CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            AbstractCard.CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            2 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int baseMagicNumber = 2;
    private static final int UPG_Number = 1;
    int cardsRetained = 0;
    boolean retained = false;

    public OverheadStrike() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it changes when upgraded.
        setMagic(baseMagicNumber, UPG_Number);
        this.tags.add(AbstractCard.CardTags.STRIKE);
        this.selfRetain = true;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }
    public void triggerOnEndOfTurnForPlayingCard() {
        retained = true;
        if((player.hasPower(RetainCardPower.POWER_ID) || player.hasPower(RetainThisTurn.ID)) &&
                !player.hasRelic(RunicPyramid.ID) && !player.hasPower(EquilibriumPower.POWER_ID)){
            cardsRetained = 0;
            for(AbstractCard c : player.hand.group) {
                if ((c.selfRetain || c.retain) || (player.hasPower(EquilibriumPower.POWER_ID) && !c.isEthereal && !c.hasTag(isEcho))) {
                    upgradeDamage(magicNumber);
                    cardsRetained++;
                }
            }
        } else {
            for(AbstractCard c : player.hand.group){
                if((c.selfRetain || c.retain) || (player.hasPower(EquilibriumPower.POWER_ID) && !c.isEthereal)){
                    upgradeDamage(magicNumber);
                }
            }
        }
    }
    public void atTurnStart(){
        if(cardsRetained > 0 && retained){
            upgradeDamage(magicNumber*(player.hand.group.size()-cardsRetained));
            cardsRetained = 0;
        }
        retained = false;
    }
}
