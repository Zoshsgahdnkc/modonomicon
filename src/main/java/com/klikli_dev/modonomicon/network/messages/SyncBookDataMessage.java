/*
 * SPDX-FileCopyrightText: 2022 klikli-dev
 *
 * SPDX-License-Identifier: MIT
 */

package com.klikli_dev.modonomicon.network.messages;

import com.klikli_dev.modonomicon.book.Book;
import com.klikli_dev.modonomicon.book.BookCategory;
import com.klikli_dev.modonomicon.data.BookDataManager;
import com.klikli_dev.modonomicon.book.BookEntry;
import com.klikli_dev.modonomicon.network.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent.Context;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SyncBookDataMessage implements Message {

    public ConcurrentMap<ResourceLocation, Book> books = new ConcurrentHashMap<>();

    public SyncBookDataMessage(ConcurrentMap<ResourceLocation, Book> books) {
        this.books = books;
    }

    public SyncBookDataMessage(FriendlyByteBuf buf) {
        this.decode(buf);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeVarInt(this.books.size());
        for (var book : this.books.values()) {
            buf.writeResourceLocation(book.getId());
            book.toNetwork(buf);

            buf.writeVarInt(book.getCategories().size());
            for (var category : book.getCategories().values()) {
                buf.writeResourceLocation(category.getId());
                category.toNetwork(buf);

                buf.writeVarInt(category.getEntries().size());
                for (var entry : category.getEntries().values()) {
                    buf.writeResourceLocation(entry.getId());
                    entry.toNetwork(buf);
                }
            }
        }
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        //build books
        int bookCount = buf.readVarInt();
        for (int i = 0; i < bookCount; i++) {
            ResourceLocation bookId = buf.readResourceLocation();
            Book book = Book.fromNetwork(bookId, buf);
            this.books.put(bookId, book);

            int categoryCount = buf.readVarInt();
            for (int j = 0; j < categoryCount; j++) {
                ResourceLocation categoryId = buf.readResourceLocation();
                BookCategory category = BookCategory.fromNetwork(categoryId, buf);

                //link category and book
                book.addCategory(category);

                int entryCount = buf.readVarInt();
                for (int k = 0; k < entryCount; k++) {
                    ResourceLocation entryId = buf.readResourceLocation();
                    BookEntry bookEntry = BookEntry.fromNetwork(entryId, buf);

                    //link entry and category
                    category.addEntry(bookEntry);
                }
            }
        }
    }


    @Override
    public void onClientReceived(Minecraft minecraft, Player player, Context context) {
        BookDataManager.get().onDatapackSyncPacket(this);
    }
}
