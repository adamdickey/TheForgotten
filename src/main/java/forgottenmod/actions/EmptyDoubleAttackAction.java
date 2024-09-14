package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;

public class EmptyDoubleAttackAction extends AbstractGameAction {

    private final int damage;
    private final AbstractMonster target;
    public EmptyDoubleAttackAction(int damage, AbstractMonster target) {
        this.damage = damage;
        this.target = target;
    }

    public void update() {
        if(player.hand.size() <= 1){
            addToBot(new DamageAction(target, new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
        }
        this.isDone = true;
    }
}
