package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class DiscardHandAttackAction extends AbstractGameAction {

    AbstractMonster monster;
    int damage = 0;

    public DiscardHandAttackAction(AbstractMonster monster, int damage) {
        this.monster = monster;
        this.damage = damage;
        this.actionType = ActionType.DISCARD;
        this.amount = player.hand.size();
    }

    public void update() {
        int handSize = player.hand.size();
        addToBot(new DiscardAction(player, player, handSize, false));
        for(int i = 0; i < handSize; i++){
            addToBot(new DamageAction(monster, new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        }
        this.isDone = true;
    }
}
