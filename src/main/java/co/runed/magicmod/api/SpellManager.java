package co.runed.magicmod.api;

import co.runed.magicmod.api.spell.Spell;
import net.minecraft.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellManager {
    private static Map<Entity, Spell> activeSpells = new HashMap<>();
    private static Map<Entity, List<Spell>> spellLibraries = new HashMap<>();

    public static void init() {
        //TODO: LOAD SPELL LIBRARIES



    }

    public static void addSpell(Entity entity, Spell spell) {

    }

    public static void setActiveSpell(Entity caster, Spell spell) {
        activeSpells.put(caster, spell);
    }

    public static Spell getActiveSpell(Entity caster) {
        if(!activeSpells.containsKey(caster)) return null;

        Spell spell = activeSpells.get(caster);

        return spell;
    }

    public static double getMana(Entity entity) {
        return 100;
    }

    public static double getMaxMana(Entity entity) {
        return 100;
    }

    public static void setMana(Entity entity) {

    }

    public static void setMaxMana(Entity entity) {

    }
}
