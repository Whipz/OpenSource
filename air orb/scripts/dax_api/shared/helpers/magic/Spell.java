package scripts.dax_api.shared.helpers.magic;

import org.tribot.api2007.Game;
import org.tribot.api2007.Magic;
import org.tribot.api2007.Skills;
import scripts.dax_api.shared.Pair;


public enum Spell implements Validatable {

    VARROCK_TELEPORT(SpellBook.Type.STANDARD, 25, "Varrock Teleport", new Pair<>(1, RuneElement.LAW), new Pair<>(3, RuneElement.AIR), new Pair<>(1, RuneElement.FIRE)),
    LUMBRIDGE_TELEPORT(SpellBook.Type.STANDARD, 31, "Lumbridge Teleport", new Pair<>(1, RuneElement.LAW), new Pair<>(3, RuneElement.AIR), new Pair<>(1, RuneElement.EARTH)),
    FALADOR_TELEPORT(SpellBook.Type.STANDARD, 37, "Falador Teleport", new Pair<>(1, RuneElement.LAW), new Pair<>(3, RuneElement.AIR), new Pair<>(1, RuneElement.WATER)),
    CAMELOT_TELEPORT(SpellBook.Type.STANDARD, 45, "Camelot Teleport", new Pair<>(1, RuneElement.LAW), new Pair<>(5, RuneElement.AIR)),
    ARDOUGNE_TELEPORT(SpellBook.Type.STANDARD, 51, "Ardougne Teleport", new Pair<>(2, RuneElement.LAW), new Pair<>(2, RuneElement.WATER)),
    KOUREND_TELEPORT(SpellBook.Type.STANDARD, 69, "Kourend Castle Teleport", new Pair<>(2, RuneElement.LAW), new Pair<>(2, RuneElement.SOUL), new Pair<>(4, RuneElement.WATER), new Pair<>(5, RuneElement.FIRE)),

    ;

    private final SpellBook.Type spellBookType;
    private final int requiredLevel;
    private final String spellName;
    private final Pair<Integer, RuneElement>[] recipe;

    Spell(SpellBook.Type spellBookType, int level, String spellName, Pair<Integer, RuneElement>... recipe) {
        this.spellBookType = spellBookType;
        this.requiredLevel = level;
        this.spellName = spellName;
        this.recipe = recipe;
    }

    public Pair<Integer, RuneElement>[] getRecipe() {
        return recipe;
    }

    public String getSpellName() {
        return spellName;
    }

    public boolean cast() {
        return canUse() && Magic.selectSpell(getSpellName());
    }

    @Override
    public boolean canUse() {
        if (SpellBook.getCurrentSpellBook() != spellBookType) {
            return false;
        }
        if (requiredLevel > Skills.SKILLS.MAGIC.getCurrentLevel()) {
            return false;
        }
        if (this == ARDOUGNE_TELEPORT && Game.getSetting(165) < 30) {
            return false;
        }

        for (Pair<Integer, RuneElement> pair : recipe) {
            int amountRequiredForSpell = pair.getKey();
            RuneElement runeElement = pair.getValue();
            if (runeElement.getCount() < amountRequiredForSpell) {
                return false;
            }
        }
        return true;
    }

}
