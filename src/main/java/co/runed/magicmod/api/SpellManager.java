package co.runed.magicmod.api;

import co.runed.magicmod.api.spell.ISpell;
import co.runed.magicmod.api.spell.Spell;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class SpellManager {
    private static boolean initialized = false;

    static Map<ServerPlayerEntity, ISpell> spells;

    public static void initialize(World world) {
        spells = new HashMap<>();

        initialized = true;
    }

    public static void setActiveSpell(ServerPlayerEntity player, ISpell spell) {
        spells.put(player, spell);
    }

    public static ISpell getActiveSpell(ServerPlayerEntity player) {
        if(!initialized) initialize(null);

        if(!spells.containsKey(player)) return null;

        ISpell spell = spells.get(player);

        return spell;
    }

    public static ISpell buildSpell(ServerPlayerEntity player) {
        if(!spells.containsKey(player)) return null;

        ISpell spell = spells.get(player);

        return spell.build();
    }

    public static boolean runSpell(ServerPlayerEntity player) {
        if(!spells.containsKey(player)) return false;

        ISpell spell = spells.get(player);

        return spell.run();
    }
}
