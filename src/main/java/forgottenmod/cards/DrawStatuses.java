package forgottenmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import forgottenmod.powers.HandSizeIncrease;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import java.util.ArrayList;
import java.util.Random;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DrawStatuses extends BaseCard {
    public static final String ID = makeID("Draw Statuses"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public DrawStatuses() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        int baseDamage = 10;
        int UPG_Damage = 2;
        int BaseMagicNumber = 2;
        int UPG_Number = 1;
        setDamage(baseDamage, UPG_Damage);
        setMagic(BaseMagicNumber, UPG_Number);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        cardsToHand(this.magicNumber);
    }
    public void cardsToHand(int amount){
        ArrayList<AbstractCard> cards = new ArrayList<>();
        for(AbstractCard c : player.drawPile.group){
            if(c.type == CardType.STATUS || c.type == CardType.CURSE){
                cards.add(c);
            }
        }
        int handSize;
        if(player.hasPower(HandSizeIncrease.ID)) {
            handSize = 10 + player.getPower(HandSizeIncrease.ID).amount;
        } else {
            handSize = 10;
        }
        for(int i = 0; i < amount; i++){
            if(!cards.isEmpty()){
                Random rand = new Random();
                AbstractCard cardToReturn = cards.get(rand.nextInt(cards.size()));
                if (player.hand.size() == handSize) {
                    player.drawPile.moveToDiscardPile(cardToReturn);
                    player.createHandIsFullDialog();
                    continue;
                }
                player.drawPile.moveToHand(cardToReturn, player.drawPile);
                cards.remove(cardToReturn);
            }
        }
    }
}
