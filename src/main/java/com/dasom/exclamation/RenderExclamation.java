package com.dasom.exclamation;
import com.mojang.blaze3d.platform.GLX;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.Identifier;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class RenderExclamation extends PlayerEntityRenderer {
    public final MinecraftClient client = MinecraftClient.getInstance();
    float r = 0.0F;
    float g = 0.0F;
    float b = 0.0F;
    float alphaFadeFactor;
    ArrayList<Integer> npc_idx;
    int plusWidth = 4;
    boolean toggle;

    public RenderExclamation(EntityRenderDispatcher renderManager, boolean toggle, ArrayList<Integer> idx) {
        super(renderManager, false);
        this.npc_idx = idx;
        this.toggle = toggle;
    }

    public void renderLabelIfPresent(AbstractClientPlayerEntity par1EntityLivingBase, double par2, double par4, double par6) {
        super.renderLabelIfPresent(par1EntityLivingBase, par2, par4, par6);
        if (par1EntityLivingBase == par1EntityLivingBase.world.getEntityById(this.npc_idx.get(0)) ||
                par1EntityLivingBase == par1EntityLivingBase.world.getEntityById(this.npc_idx.get(1)) ||
                par1EntityLivingBase == par1EntityLivingBase.world.getEntityById(this.npc_idx.get(2)) ||
                par1EntityLivingBase == par1EntityLivingBase.world.getEntityById(this.npc_idx.get(3)) ||
                par1EntityLivingBase == par1EntityLivingBase.world.getEntityById(this.npc_idx.get(4)) ||
                par1EntityLivingBase == par1EntityLivingBase.world.getEntityById(this.npc_idx.get(5)) ||
                par1EntityLivingBase == par1EntityLivingBase.world.getEntityById(this.npc_idx.get(6))) {
//        if (par1EntityLivingBase != this.renderManager.camera.getFocusedEntity()) {
            this.alphaFadeFactor = 1.0F;
//            if (this.toggle) {this.alphaFadeFactor = 1.0F;}
//            else {this.alphaFadeFactor = 0.0F;}
            this.r = 1.0F;
            this.g = 1.0F;
            this.b = 1.0F;
//            MinecraftClient.getInstance().player.sendChatMessage(String.valueOf(npcid));
            renderImg((PlayerEntity) par1EntityLivingBase, par2, par4, par6);
        }
    }

    protected void renderImg(PlayerEntity par1EntityPlayer, double par2, double par4, double par6) {
        double var10 = par1EntityPlayer.squaredDistanceTo(this.renderManager.camera.getFocusedEntity());
        float var12 = par1EntityPlayer.isSneaking() ? 32.0F : 64.0F;
        int brightness = 15728880;
        int brightMod = brightness % 65536;
        int brightDiv = brightness / 65536;
        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, brightMod / 1.0F, brightDiv / 1.0F);
        if (var10 <= (var12 * var12)) {
            float var13 = 1.6F;
            float var14 = 0.016666668F * var13;
            GLShim.glPushMatrix();
            GLShim.glTranslatef((float) par2 + 0.0F, (float) par4 + 2.3F, (float) par6);
            GLShim.glNormal3f(0.0F, 1.0F, 0.0F);
            GLShim.glRotatef(-this.renderManager.cameraYaw, 0.0F, 1.0F, 0.0F);
            GLShim.glRotatef(this.renderManager.cameraPitch, 1.0F, 0.0F, 0.0F);
            GLShim.glScalef(-var14, -var14, var14);
            GLShim.glDisable(2896);
            GLShim.glDisable(2912);
            GLShim.glDepthMask(false);
            GLShim.glEnable(3042);
            GLShim.glBlendFunc(770, 771);

            int left = -9;
            int right = 9;
            Tessellator var15 = Tessellator.getInstance();
            int top = -20;
            int bottom = -2;

            GLShim.glEnable(32823);
            GLShim.glPolygonOffset(1.0F, 3.0F);
            GLShim.glEnable(2929);
            GLShim.glDepthMask(true);
            drawExclamation(this.r, this.g, this.b, this.alphaFadeFactor, top, bottom, left, right, var15, 3.0F);

            GLShim.glDisable(32823);
            GLShim.glEnable(2912);
            GLShim.glEnable(2896);
            GLShim.glDisable(3042);
            GLShim.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GLShim.glPopMatrix();
            }
        }


    private void drawExclamation(float r, float g, float b, float a, float top, float bottom, float left, float right, Tessellator var15, float offset) {
        img("images/exclamation.png");
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexBuffer = tessellator.getBuffer();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex(left, top, 0.0D).texture(0.0625D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex(left, bottom, 0.0D).texture(0.0625D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, bottom, 0.0D).texture(0.9375D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, top, 0.0D).texture(0.9375D, 0.125D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex((left - this.plusWidth), top, 0.0D).texture(0.0D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex((left - this.plusWidth), bottom, 0.0D).texture(0.0D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex(left, bottom, 0.0D).texture(0.0625D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex(left, top, 0.0D).texture(0.0625D, 0.125D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex(right, top, 0.0D).texture(0.9375D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, bottom, 0.0D).texture(0.9375D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex((right + this.plusWidth), bottom, 0.0D).texture(1.0D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex((right + this.plusWidth), top, 0.0D).texture(1.0D, 0.125D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex(left, (top - this.plusWidth), 0.0D).texture(0.0625D, 0.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(left, top, 0.0D).texture(0.0625D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, top, 0.0D).texture(0.9375D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, (top - this.plusWidth), 0.0D).texture(0.9375D, 0.0D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex(left, bottom, 0.0D).texture(0.0625D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex(left, (bottom + this.plusWidth), 0.0D).texture(0.0625D, 1.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(-2.0D, (bottom + this.plusWidth), 0.0D).texture(0.5D, 1.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(-2.0D, bottom, 0.0D).texture(0.5D, 0.875D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex(-2.0D, bottom, 0.0D).texture(0.5D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex(-2.0D, (bottom + this.plusWidth), 0.0D).texture(0.5D, 1.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(6.0D, (bottom + this.plusWidth), 0.0D).texture(0.625D, 1.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(6.0D, bottom, 0.0D).texture(0.625D, 0.875D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex(6.0D, bottom, 0.0D).texture(0.625D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex(6.0D, (bottom + this.plusWidth), 0.0D).texture(0.625D, 1.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, (bottom + this.plusWidth), 0.0D).texture(0.9375D, 1.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, bottom, 0.0D).texture(0.9375D, 0.875D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex((left - this.plusWidth), (top - this.plusWidth), 0.0D).texture(0, 0).color(r, g, b, a).next();
        vertexBuffer.vertex((left - this.plusWidth), top, 0.0D).texture(0.0D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex(left, top, 0.0D).texture(0.0625D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex(left, (top - this.plusWidth), 0.0D).texture(0.0625D, 0.0D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex(right, (top - this.plusWidth), 0.0D).texture(0.9375D, 0.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, top, 0.0D).texture(0.9375D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex((right + this.plusWidth), top, 0.0D).texture(1.0D, 0.125D).color(r, g, b, a).next();
        vertexBuffer.vertex((right + this.plusWidth), (top - this.plusWidth), 0.0D).texture(1, 0).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex((left - this.plusWidth), bottom, 0.0D).texture(0.0D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex((left - this.plusWidth), (bottom + this.plusWidth), 0.0D).texture(0, 1).color(r, g, b, a).next();
        vertexBuffer.vertex(left, (bottom + this.plusWidth), 0.0D).texture(0.0625D, 1.0D).color(r, g, b, a).next();
        vertexBuffer.vertex(left, bottom, 0.0D).texture(0.0625D, 0.875D).color(r, g, b, a).next();
        var15.draw();
        vertexBuffer.begin(7, VertexFormats.POSITION_TEXTURE_COLOR);
        vertexBuffer.vertex(right, bottom, 0.0D).texture(0.9375D, 0.875D).color(r, g, b, a).next();
        vertexBuffer.vertex(right, (bottom + this.plusWidth), 0.0D).texture(0.9375D, 1.0D).color(r, g, b, a).next();
        vertexBuffer.vertex((right + this.plusWidth), (bottom + this.plusWidth), 0.0D).texture(1, 1).color(r, g, b, a).next();
        vertexBuffer.vertex((right + this.plusWidth), bottom, 0.0D).texture(1.0D, 0.875D).color(r, g, b, a).next();
        var15.draw();
        GLShim.glPolygonOffset(1.0F, offset - 1.0F);
    }

    public void img(String paramStr) {
        this.renderManager.textureManager.bindTexture(new Identifier("exclamation", paramStr));
    }
}