package com.dasom.exclamation.fabricmod.mixins;
import com.dasom.exclamation.Exclamation;
import com.dasom.exclamation.fabricmod.FabricModExclamation;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.client.gui.hud.ChatListenerHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ChatListenerHud.class})
public class MixinExclaListenerHud {
    @Inject(method = {"onChatMessage(Lnet/minecraft/network/MessageType;Lnet/minecraft/text/Text;)V"}, at = {@At("HEAD")}, cancellable = true)
    public void postSay(MessageType type, Text textComponent, CallbackInfo ci) {
        FabricModExclamation.say(textComponent);
    }
}
