package co.runed.magicmod.api.spell;

import co.runed.magicmod.MagicMod;
import co.runed.magicmod.api.spell.Spell;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.Tag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SpellManager {
    private static Path SPELL_DIRECTORY = Paths.get(MagicMod.CONFIG_DIRECTORY.toString(), "spells");

    private static final Map<UUID, Spell> activeSpells = new HashMap<>();
    private static Map<UUID, List<Spell>> spellLibraries = new HashMap<>();

    public static void loadLibraries() {
        File folder = SPELL_DIRECTORY.toFile();

        if(folder != null && folder.exists() && folder.isDirectory()) {
            for (File file : folder.listFiles()) {

                CompoundTag tag = null;
                try {
                    tag = NbtIo.read(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(!tag.containsKey("spells")) continue;

                ListTag spellList = tag.getList("spells", NbtType.COMPOUND);

                for (Tag spellTag : spellList) {
                    if(spellTag instanceof CompoundTag) {
                        CompoundTag spellCompound = (CompoundTag)spellTag;

                        Spell spell = Spell.fromTag(spellCompound);

                        String a = file.getName();

                        UUID uuid = UUID.fromString(file.getName().substring(0, file.getName().lastIndexOf(".")));

                        addSpell(uuid, spell);
                    }
                }
            }
        }
    }

    public static void saveLibraries() throws IOException {
        File folder = SPELL_DIRECTORY.toFile();

        Files.createDirectories(SPELL_DIRECTORY);

        for (Map.Entry<UUID, List<Spell>> entry : spellLibraries.entrySet()) {
            UUID uuid = entry.getKey();

            CompoundTag tag = new CompoundTag();
            ListTag spellListTag = new ListTag();

            for (Spell spell : entry.getValue()) {
                spellListTag.add(spell.toTag());
            }

            tag.put("spells", spellListTag);


            Path filePath = Paths.get(SPELL_DIRECTORY.toString(), uuid.toString() + ".dat");

            if(!filePath.toFile().exists()) {
                Files.createFile(filePath);
            }

            NbtIo.write(tag, filePath.toFile());
        }
    }

    public static void addSpell(UUID uuid, Spell spell) {
        if(!spellLibraries.containsKey(uuid)) spellLibraries.put(uuid, new ArrayList<>());

        List<Spell> spellLibrary = spellLibraries.get(uuid);
        if(!spellLibrary.contains(spell)) spellLibrary.add(spell);

        try {
            saveLibraries();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addSpell(Entity entity, Spell spell) {
        addSpell(entity.getUuid(), spell);
    }

    public static void setActiveSpell(Entity caster, Spell spell) {
        activeSpells.put(caster.getUuid(), spell);
    }

    public static Spell getActiveSpell(Entity caster) {
        if(!activeSpells.containsKey(caster.getUuid())) return null;

        return activeSpells.get(caster.getUuid());
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
