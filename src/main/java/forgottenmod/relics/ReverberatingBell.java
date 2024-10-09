package forgottenmod.relics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import theforgotten.TheForgotten;

import static forgottenmod.BasicMod.makeID;

public class ReverberatingBell extends BaseRelic {
    private static final String NAME = "Reverberating Bell"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.

    public ReverberatingBell() {
        super(ID, NAME, TheForgotten.Enums.CARD_COLOR, RARITY, SOUND);
    }
    public String getUpdatedDescription(){
        return this.DESCRIPTIONS[0];
    }
    public void onEquip(){
        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
    }
}