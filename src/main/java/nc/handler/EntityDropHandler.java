package nc.handler;

import java.util.Random;

import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import nc.NuclearCraft;
import nc.item.NCItems;

public class EntityDropHandler {

    public Random rand = new Random();

    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onEntityDrop(LivingDropsEvent event) {
        if (NuclearCraft.extraDrops && event.entity.worldObj.getGameRules()
                .getGameRuleBooleanValue("doMobLoot")) {
            if (event.entityLiving instanceof EntityMob) {
                if (rand.nextInt(100) < 5) event.entityLiving.dropItem(NCItems.dUBullet, 1 + rand.nextInt(2));
            }
        }
    }
}
