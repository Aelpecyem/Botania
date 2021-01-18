/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.data.util;

import com.google.gson.JsonArray;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jdk.internal.jline.internal.Nullable;

public class OverrideHolder {
	final List<ModelOverride> modelOverrides = new ArrayList<>();

	@SafeVarargs
	public final OverrideHolder add(Identifier model, Pair<Identifier, Double>... preds) {
		Map<Identifier, Double> predMap = new LinkedHashMap<>();
		for (Pair<Identifier, Double> pred : preds) {
			predMap.put(pred.getFirst(), pred.getSecond());
		}
		modelOverrides.add(new ModelOverride(predMap, model));
		return this;
	}

	@Nullable
	public JsonArray toJson() {
		if (modelOverrides.isEmpty()) {
			return null;
		} else {
			JsonArray ret = new JsonArray();
			modelOverrides.stream().map(ModelOverride::toJson).forEach(ret::add);
			return ret;
		}
	}
}
