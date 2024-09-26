package forgottenmod.actions;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.*;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theforgotten.TheForgotten;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

@SpirePatch2(clz = AbstractDungeon.class, method = "initializeRelicList")
public class RemoveRelicsPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert() {
        if(player.chosenClass == TheForgotten.Enums.TheForgotten){
            relicsToRemoveOnStart.add(DarkstonePeriapt.ID);
            relicsToRemoveOnStart.add(DuVuDoll.ID);
            relicsToRemoveOnStart.add(CeramicFish.ID);
            relicsToRemoveOnStart.add(TinyHouse.ID);

            for (String remove : relicsToRemoveOnStart) {
                Iterator<String> s;
                for (s = commonRelicPool.iterator(); s.hasNext(); ) {
                    String derp = s.next();
                    if (derp.equals(remove)) {
                        s.remove();
                    }
                }
                for (s = uncommonRelicPool.iterator(); s.hasNext(); ) {
                    String derp = s.next();
                    if (derp.equals(remove)) {
                        s.remove();
                    }
                }
                for (s = rareRelicPool.iterator(); s.hasNext(); ) {
                    String derp = s.next();
                    if (derp.equals(remove)) {
                        s.remove();
                    }
                }
                for (s = bossRelicPool.iterator(); s.hasNext(); ) {
                    String derp = s.next();
                    if (derp.equals(remove)) {
                        s.remove();
                    }
                }
                for (s = shopRelicPool.iterator(); s.hasNext(); ) {
                    String derp = s.next();
                    if (derp.equals(remove)) {
                        s.remove();
                    }
                }
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ArrayList.class, "clear");
            return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
        }
    }
}
