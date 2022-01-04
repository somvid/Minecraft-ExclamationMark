package com.dasom.exclamation.fabricmod;
import com.dasom.exclamation.Exclamation;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class FabricModExclamation implements ClientModInitializer, ClientTickCallback {
    static FabricModExclamation instance;

    MinecraftClient minecraft;
    Exclamation exclamation;

    private boolean initialized = false;

    public void onInitializeClient() {
        ClientTickCallback.EVENT.register(this);
        instance = this;
    }

    public void lateInit() {
        this.minecraft = MinecraftClient.getInstance();
        this.exclamation = new Exclamation();
    }

    public void tick(MinecraftClient client) {
        if (!this.initialized) {
            if (client != null && client.getOverlay() == null) {
                lateInit();
                this.initialized = true;
            }
        } else {
            Entity renderViewEntity = this.minecraft.getCameraEntity();
            boolean inGame = (renderViewEntity != null && renderViewEntity.world != null);
            if (inGame)
                this.exclamation.onTickInGame(this.minecraft);
        }
    }
    public static void say(Text textComponent) {
        instance.exclamation.clientString(textComponent.getString());
    }
}
