package forgottenmod.cards;

import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnCreateCardInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.actions.EchoAction;
import forgottenmod.powers.CardsCreatedThisTurn;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class CreateReduceCost extends BaseCard implements OnCreateCardInterface {
    public static final String ID = makeID("Create Reduce Cost"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public CreateReduceCost() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        int baseDamage = 8;
        int UPG_Damage = 3;
        setDamage(baseDamage, UPG_Damage);
    }
    public void triggerWhenDrawn() {
        addToBot(new EchoAction(this, 1));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
    }

    public void atTurnStart() {
        resetAttributes();
        applyPowers();
    }
    public void applyPowers(){
        super.applyPowers();
        if(player.hasPower(CardsCreatedThisTurn.ID)){
            if(player.getPower(CardsCreatedThisTurn.ID).amount > this.cost){
                this.setCostForTurn(0);
            } else {
                this.setCostForTurn(this.cost - player.getPower(CardsCreatedThisTurn.ID).amount);
            }
        }
    }

    @Override
    public void onCreateCard(AbstractCard abstractCard) {
        this.applyPowers();
    }
}
