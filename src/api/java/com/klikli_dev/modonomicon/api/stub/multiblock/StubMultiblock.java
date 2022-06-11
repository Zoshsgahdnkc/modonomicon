/*
 * LGPL-3.0
 *
 * Copyright (C) 2022 Authors of Patchouli
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

package com.klikli_dev.modonomicon.api.stub.multiblock;

import com.klikli_dev.modonomicon.api.ModonomiconAPI;
import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;

import java.util.Collection;
import java.util.Collections;

public class StubMultiblock implements Multiblock {

    public static final ResourceLocation TYPE = new ResourceLocation(ModonomiconAPI.ID + ":stub");

    public static final StubMultiblock INSTANCE = new StubMultiblock();

    private StubMultiblock() {
    }

    @Override
    public ResourceLocation getType() {
        return TYPE;
    }

    @Override
    public Multiblock offset(int x, int y, int z) {
        return this;
    }

    @Override
    public Multiblock offsetView(int x, int y, int z) {
        return this;
    }

    @Override
    public Multiblock setSymmetrical(boolean symmetrical) {
        return this;
    }

    @Override
    public Multiblock setId(ResourceLocation res) {
        return this;
    }

    @Override
    public boolean isSymmetrical() {
        return false;
    }

    @Override
    public ResourceLocation getId() {
        return new ResourceLocation("patchouli", "stub");
    }

    @Override
    public void place(Level world, BlockPos pos, Rotation rotation) {
        // NO-OP
    }

    @Override
    public Pair<BlockPos, Collection<SimulateResult>> simulate(Level world, BlockPos anchor, Rotation rotation, boolean forView, boolean disableOffset) {
        return Pair.of(BlockPos.ZERO, Collections.emptyList());
    }

    @Override
    public Rotation validate(Level world, BlockPos pos) {
        return null;
    }

    @Override
    public boolean validate(Level world, BlockPos pos, Rotation rotation) {
        return false;
    }

    @Override
    public boolean test(Level world, BlockPos start, int x, int y, int z, Rotation rotation) {
        return false;
    }

    @Override
    public Vec3i getSize() {
        return Vec3i.ZERO;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer) {

    }

}
