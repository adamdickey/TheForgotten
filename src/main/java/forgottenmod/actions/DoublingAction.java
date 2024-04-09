package forgottenmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class DoublingAction extends AbstractGameAction {

    private final DamageInfo info;
    private final AbstractCard card;

    public DoublingAction(AbstractCreature target, DamageInfo info, AbstractCard card) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = 0.1F;
        this.card = card;
    }

    @Override
    public void update() {
        if (this.duration == 0.1F &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            this.target.damage(this.info);
            if (((this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead &&
                    !this.target.hasPower("Minion")) {
                AbstractCard echo = card.makeStatEquivalentCopy();
                echo.baseDamage = card.baseDamage * 2;
                addToBot(new MakeTempCardInHandAction(echo, false));
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();
        }
        tickDuration();
    }
}
