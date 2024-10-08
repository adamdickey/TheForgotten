package forgottenmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import forgottenmod.actions.HandSelectAction;
import forgottenmod.actions.StoreOtherCardAction;
import forgottenmod.powers.StoredPower;
import forgottenmod.util.CardStats;
import theforgotten.TheForgotten;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class CardStoringDefend extends BaseCard {
    public static final String ID = makeID("Card Storing Defend"); //makeID adds the mod ID, so the final ID will be something like "modID:MyCard"
    private static final CardStats info = new CardStats(
            TheForgotten.Enums.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public CardStoringDefend() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        int baseBlock = 8;
        int UPG_Block = 2;
        setBlock(baseBlock, UPG_Block);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        if(this.upgraded) {
            if (p.hand.size() == 1) {
                return;
            }
            else if (p.hand.size() == 2 && !player.hasPower(StoredPower.ID)) {
                for (AbstractCard c : player.hand.group) {
                    if (c != this) {
                        c.setCostForTurn(0);
                        return;
                    }
                }
            }
            if(player.hasPower(StoredPower.ID)){
                if(player.getPower(StoredPower.ID).amount == 3){
                    float dialogX = player.dialogX;
                    float dialogY = player.dialogY;
                    AbstractDungeon.effectList.add(new ThoughtBubble(dialogX, dialogY, 3.0F, "#rStorage #ris #rFull!", true));
                    return;
                }
            }
            addToBot(new HandSelectAction(1, c -> true, list -> {
                for (AbstractCard c : list) {
                    addToBot(new StoreOtherCardAction(c, true, player.hand));
                }
                list.clear();
                }, null, "Store", false, false, false));
            }
        else{
            if(p.hand.size() == 1){
                return;
            }
            else if(p.hand.size() == 2 && !player.hasPower(StoredPower.ID)){
                for(AbstractCard c : player.hand.group){
                    if(c != this){
                        c.setCostForTurn(0);
                        return;
                    }
                }
            }
            AbstractCard randomCard = player.hand.getRandomCard(true);
            while(randomCard == this){
                randomCard = player.hand.getRandomCard(true);
            }
            addToBot(new StoreOtherCardAction(randomCard, true, player.hand));
        }
    }
}
