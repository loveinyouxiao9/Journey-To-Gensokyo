/**
 * This class was created by <Katrix>, base on a class from Touhou Items Mod. It's distributed as
 * part of the Journey To Gensokyo Mod. Get the Source Code in github:
 * https://github.com/Katrix-/JTG
 *
 * Journey To Gensokyo is Open Source and distributed under the
 * a modifed Botania license: https://github.com/Katrix-/JTG/blob/master/LICENSE.md
 */

package katrix.journeyToGensokyo.plugin.thsc.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cpw.mods.fml.common.registry.EntityRegistry;
import katrix.journeyToGensokyo.JourneyToGensokyo;
import katrix.journeyToGensokyo.handler.ConfigHandler;
import katrix.journeyToGensokyo.lib.LibEntityName;
import katrix.journeyToGensokyo.lib.LibMobID;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import thKaguyaMod.DanmakuConstants;
import thKaguyaMod.entity.living.EntityDanmakuMob;
import thKaguyaMod.entity.living.EntityTHFairy;
import thKaguyaMod.init.THKaguyaConfig;
import thKaguyaMod.registry.DanmakuPatternRegistry;

public class EntityTHFairyIce extends EntityTHFairy {

	public EntityTHFairyIce(World world) {
		super(world);

		experienceValue = 15;
		shotColor = (byte)DanmakuConstants.AQUA;

		setForm((byte)1);

		setMaxHP(10.0F);
		setHealth(10.0F);

		setSpeed(0.6D);
		this.setSpecies(EntityDanmakuMob.SPECIES_FAIRY, EntityDanmakuMob.SPECIES_FAIRY_ICE);

		setAttackDistance(12.0D);
		setDetectionDistance(12.0D);
		setFlyingHeight(3);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData) {
		Object p_110161_1_1 = super.onSpawnWithEgg(entityLivingData);

		EntityFamiliarIce familiar = new EntityFamiliarIce(worldObj);
		familiar.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0F);
		worldObj.spawnEntityInWorld(familiar);
		familiar.mountEntity(this);

		return (IEntityLivingData)p_110161_1_1;
	}

	@Override
	public void onUpdate() {
		if (getHealth() <= 0) {
			motionX = 0.0D;
			motionY = 0.0D;
			motionZ = 0.0D;
		}

		if (ticksExisted <= lastTime)
			return;
		else {
			super.onUpdate();
			if (attackCounter > danmakuSpan) {
				attackCounter = 0;
			}
		}
	}

	@Override
	protected void setPattern(int patternId) {
		String patternKey = DanmakuPatternRegistry.pattern.get(patternId);

		setDanmakuPattern(DanmakuPatternRegistry.danmaku.get(patternKey));
		danmakuSpan = DanmakuPatternRegistry.span.get(patternKey);
		speedRate = DanmakuPatternRegistry.speed.get(patternKey);
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float amount) {
		if (!damageSource.isMagicDamage()) {
			amount *= 0.5F;
		}
		return super.attackEntityFrom(damageSource, amount);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 3;
	}

	public static void postInit() {
		EntityRegistry.registerModEntity(EntityTHFairyIce.class, LibEntityName.FAIRY_ICE, LibMobID.FAIRY_ICE, JourneyToGensokyo.instance, 80, 1, true);

		List<BiomeGenBase> spawnbiomes = new ArrayList<BiomeGenBase>(Arrays.asList(BiomeDictionary.getBiomesForType(Type.COLD)));
		for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(Type.SNOWY)) {
			if (!spawnbiomes.contains(biome)) {
				spawnbiomes.add(biome);
			}
		}
		for (BiomeGenBase biome : BiomeDictionary.getBiomesForType(Type.END)) {
			if (spawnbiomes.contains(biome)) {
				spawnbiomes.remove(biome);
			}
		}
		if (THKaguyaConfig.spawnDanmakuMob && ConfigHandler.newFariesSpawn) {
			EntityRegistry.addSpawn(EntityTHFairyIce.class, 20, 1, 3, EnumCreatureType.monster, spawnbiomes.toArray(new BiomeGenBase[0]));
		}
	}
}