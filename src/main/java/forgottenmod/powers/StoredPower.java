package forgottenmod.powers;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.TimeWarpPower;
import forgottenmod.actions.unStorageModifier;
import forgottenmod.cards.*;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static forgottenmod.BasicMod.makeID;

public class StoredPower extends BasePower implements InvisiblePower {

    public static final String ID = makeID("Stored");
    private final AbstractCard card;
    boolean endOfTurn = false;
    boolean triggered = false;
    ArrayList<AbstractCard> storedCards = new ArrayList<>();
    public StoredPower(AbstractPlayer player, int amount, AbstractCard card) {
        super(ID, PowerType.BUFF, false, player, player, 1, false);
        this.amount = amount;
        this.card = card;
        storedCards.add(this.card);
    }
    public void onInitialApplication() {
        updateDescription();
        addRenderedCard(card);
        if(player.hand.isEmpty()){
            this.onDrawOrDiscard();
        }
    }
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer){
        endOfTurn = true;
    }
    public void atStartOfTurn(){
        endOfTurn = false;
        if(storedCards.size() > player.limbo.size()){
            for(AbstractCard c : storedCards){
                addRenderedCard(c);
            }
        }
    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power instanceof StoredPower){
            storedCards.add(((StoredPower) power).card);
            updateDescription();
            if(((StoredPower) power).card instanceof Reorganize){
                addToBot(new DrawCardAction(1));
            }
            addRenderedCard(((StoredPower) power).card);
            if(player.hand.isEmpty()){
                this.onDrawOrDiscard();
            }
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        triggered = false;
        if(card instanceof Recollect){
            for(AbstractCard c : storedCards){
                AbstractCard tmp = c.makeSameInstanceOf();
                tmp.purgeOnUse = true;
                AbstractMonster monster = AbstractDungeon.getRandomMonster();
                tmp.calculateCardDamage(monster);
                addToBot(new NewQueueCardAction(tmp, monster, false, true));
                if(c instanceof StoreStrength){
                    addToBot(new ApplyPowerAction(player, player, new StrengthPower(player, c.magicNumber), c.magicNumber));
                }
            }
        }
    }
    public void onDrawOrDiscard(){
        if(player.hand.isEmpty() && !endOfTurn && !triggered){
            triggered = true;
           storedCardToHand();
        }
    }
    public void storedCardToHand(){
        if(!storedCards.isEmpty() && !AbstractDungeon.getCurrRoom().monsters.areMonstersDead()){
            AbstractCard cardToReturn = storedCards.get(0);
            CardModifierManager.addModifier(cardToReturn, new unStorageModifier());
            addToBot(new ExhaustToHandAction(cardToReturn));
            addToBot(new UnlimboAction(cardToReturn));
            cardToReturn.setCostForTurn(0);
            if(cardToReturn instanceof StoreStrength){
                addToBot(new ApplyPowerAction(player, player, new StrengthPower(player, cardToReturn.magicNumber)));
                addToBot(new ExhaustSpecificCardAction(cardToReturn, player.hand));
                triggered = false;
            }
            if(player.getPower(StoredPower.ID).amount > 1){
                addToBot(new ReducePowerAction(player, player, StoredPower.ID, 1));
            } else {
                addToBot(new RemoveSpecificPowerAction(player, player, StoredPower.ID));
            }
            updateDescription();
            if(!storedCards.isEmpty()){
                removeRenderedCard();
                storedCards.remove(0);
            }
        }
    }

    public void updateDescription() {
        this.description = "Stored: ";
        if(!storedCards.isEmpty()){
            for(AbstractCard c : storedCards){
                this.description = this.description + FontHelper.colorString(c.name, "y") + ", ";
            }
        }
    }
    public void addRenderedCard(AbstractCard card){
        AbstractCard tmp = card.makeSameInstanceOf();
        tmp.targetDrawScale = 0.4F;
        tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale - 150F * (storedCards.indexOf(card)) * Settings.scale;
        tmp.target_y = Settings.HEIGHT / 2.0F + 250.0F * Settings.scale;
        tmp.selfRetain = false;
        player.limbo.addToTop(tmp);
    }
    public void removeRenderedCard(){
        if(!storedCards.isEmpty() && !AbstractDungeon.getCurrRoom().monsters.areMonstersDead()){
            if(!player.limbo.isEmpty()){
                player.limbo.removeCard(player.limbo.group.get(0));
                for(int i = 0; i < player.limbo.size(); i++){
                    player.limbo.group.get(i).target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale - 150F * (i) * Settings.scale;
                    player.limbo.group.get(i).target_y = Settings.HEIGHT / 2.0F + 250.0F * Settings.scale;
                }
            }
        }
    }
}