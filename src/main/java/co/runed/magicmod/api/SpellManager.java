package co.runed.magicmod.api;

import co.runed.magicmod.api.spell.ISpell;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellManager {
    private static Map<Entity, ISpell> activeSpells = new HashMap<>();
    private static Map<Entity, List<ISpell>> spellLibraries = new HashMap<>();

    public static void init(World world) {
        //TODO: LOAD SPELL LIBRARIES



    }

    public static void addSpell(Entity entity, ISpell spell) {

    }

    public static void setActiveSpell(Entity caster, ISpell spell) {
        activeSpells.put(caster, spell);
    }

    public static ISpell getActiveSpell(Entity caster) {
        if(!activeSpells.containsKey(caster)) return null;

        ISpell spell = activeSpells.get(caster);

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
