package nc.handler;

import cpw.mods.fml.common.registry.EntityRegistry;
import nc.NuclearCraft;

public class EntityHandler {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void registerNuke(Class entityClass, String name) {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
        EntityRegistry.registerModEntity(entityClass, name, entityId, NuclearCraft.instance, 64, 1, true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void registerEMP(Class entityClass, String name) {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
        EntityRegistry.registerModEntity(entityClass, name, entityId, NuclearCraft.instance, 64, 1, true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void registerAntimatterBomb(Class entityClass, String name) {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
        EntityRegistry.registerModEntity(entityClass, name, entityId, NuclearCraft.instance, 64, 1, true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void registerNuclearGrenade(Class entityClass, String name) {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();

        EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
        EntityRegistry.registerModEntity(entityClass, name, entityId, NuclearCraft.instance, 64, 1, true);
    }
}
