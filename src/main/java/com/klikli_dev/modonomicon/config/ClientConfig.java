/*
 * LGPL-3-0
 *
 * Copyright (C) 2021 klikli-dev
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.klikli_dev.modonomicon.config;

import com.klikli_dev.modonomicon.config.value.CachedBoolean;
import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig extends ConfigBase {

    private static final ClientConfig instance = new ClientConfig();

    public final SampleCategory sampleCategory;
    public final ForgeConfigSpec spec;

    private ClientConfig() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        this.sampleCategory = new SampleCategory(this, builder);
        this.spec = builder.build();
    }

    public static ClientConfig get() {
        return instance;
    }

    public static class SampleCategory extends ConfigCategoryBase {
        public final CachedBoolean sampleBoolean;

        public SampleCategory(IConfigCache parent, ForgeConfigSpec.Builder builder) {
            super(parent, builder);
            builder.comment("Sample Settings").push("sample");
            this.sampleBoolean = CachedBoolean.cache(this,
                    builder.comment("Sample Boolean.")
                            .define("sampleBoolean", true));
            builder.pop();
        }
    }
}
