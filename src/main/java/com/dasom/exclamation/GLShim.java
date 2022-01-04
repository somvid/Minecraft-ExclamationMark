package com.dasom.exclamation;

import com.mojang.blaze3d.platform.GlStateManager;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.opengl.GL11;

public class GLShim {
    public static final int GL_ALPHA_TEST = 3008;
    public static final int GL_BLEND = 3042;
    public static final int GL_CLAMP = 10496;
    public static final int GL_CLAMP_TO_EDGE = 33071;

    public static final int GL_COLOR_BUFFER_BIT = 16384;

    public static final int GL_COLOR_CLEAR_VALUE = 3106;

    public static final int GL_CULL_FACE = 2884;

    public static final int GL_DEPTH_BUFFER_BIT = 256;

    public static final int GL_DST_ALPHA = 772;

    public static final int GL_DST_COLOR = 774;

    public static final int GL_FOG = 2912;

    public static final int GL_DEPTH_TEST = 2929;

    public static final int GL_FLAT = 7424;

    public static final int GL_FOG_DENSITY = 2914;

    public static final int GL_FOG_END = 2916;

    public static final int GL_FOG_MODE = 2917;

    public static final int GL_FOG_START = 2915;

    public static final int GL_GENERATE_MIPMAP = 33169;

    public static final int GL_GREATER = 516;

    public static final int GL_LIGHTING = 2896;

    public static final int GL_LINEAR = 9729;

    public static final int GL_LINES = 1;

    public static final int GL_LINEAR_MIPMAP_LINEAR = 9987;

    public static final int GL_LINEAR_MIPMAP_NEAREST = 9985;

    public static final int GL_MODELVIEW = 5888;

    public static final int GL_NEAREST = 9728;

    public static final int GL_NEAREST_MIPMAP_LINEAR = 9986;

    public static final int GL_NEAREST_MIPMAP_NEAREST = 9984;

    public static final int GL_NORMALIZE = 2977;

    public static final int GL_ONE = 1;

    public static final int GL_ONE_MINUS_DST_ALPHA = 773;

    public static final int GL_ONE_MINUS_DST_COLOR = 775;

    public static final int GL_ONE_MINUS_SRC_ALPHA = 771;

    public static final int GL_ONE_MINUS_SRC_COLOR = 769;

    public static final int GL_PACK_ALIGNMENT = 3333;

    public static final int GL_POLYGON_OFFSET_FILL = 32823;

    public static final int GL_PROJECTION = 5889;

    public static final int GL_PROJECTION_MATRIX = 2983;

    public static final int GL_QUADS = 7;

    public static final int GL_RGBA = 6408;

    public static final int GL_SMOOTH = 7425;

    public static final int GL_SCISSOR_TEST = 3089;

    public static final int GL_SRC_ALPHA = 770;

    public static final int GL_TEXTURE_2D = 3553;

    public static final int GL_TEXTURE_HEIGHT = 4097;

    public static final int GL_TEXTURE_MAG_FILTER = 10240;

    public static final int GL_TEXTURE_MIN_FILTER = 10241;

    public static final int GL_TEXTURE_WIDTH = 4096;

    public static final int GL_TEXTURE_WRAP_S = 10242;

    public static final int GL_TEXTURE_WRAP_T = 10243;

    public static final int GL_TRUE = 1;

    public static final int GL_TRANSFORM_BIT = 4096;

    public static final int GL_UNPACK_ALIGNMENT = 3317;

    public static final int GL_UNPACK_ROW_LENGTH = 3314;

    public static final int GL_UNPACK_SKIP_PIXELS = 3316;

    public static final int GL_UNPACK_SKIP_ROWS = 3315;

    public static final int GL_UNSIGNED_BYTE = 5121;

    public static final int GL_UNSIGNED_INT_8_8_8_8 = 32821;

    public static final int GL_VIEWPORT_BIT = 2048;

    public static final int GL_ZERO = 0;

    public static final int GL_BGRA = 32993;

    public static final int GL_RESCALE_NORMAL = 32826;

    public static final int GL_UNSIGNED_INT_8_8_8_8_REV = 33639;

    public static void glEnable(int attrib) {
        switch (attrib) {
            case 3008:
                GlStateManager.enableAlphaTest();
                break;
            case 3042:
                GlStateManager.enableBlend();
                break;
            case 2884:
                GlStateManager.enableCull();
                break;
            case 2929:
                GlStateManager.enableDepthTest();
                break;
            case 2912:
                GlStateManager.enableFog();
                break;
            case 2896:
                GlStateManager.enableLighting();
                break;
            case 2977:
                GlStateManager.enableNormalize();
                break;
            case 32823:
                GlStateManager.enablePolygonOffset();
                break;
            case 32826:
                GlStateManager.enableRescaleNormal();
                break;
            case 3553:
                GlStateManager.enableTexture();
                break;
            case 3089:
                GL11.glEnable(3089);
                break;
        }
    }

    public static void glDisable(int attrib) {
        switch (attrib) {
            case 3008:
                GlStateManager.disableAlphaTest();
                break;
            case 3042:
                GlStateManager.disableBlend();
                break;
            case 2884:
                GlStateManager.disableCull();
                break;
            case 2929:
                GlStateManager.disableDepthTest();
                break;
            case 2912:
                GlStateManager.disableFog();
                break;
            case 2896:
                GlStateManager.disableLighting();
                break;
            case 2977:
                GlStateManager.disableNormalize();
                break;
            case 32823:
                GlStateManager.disablePolygonOffset();
                break;
            case 32826:
                GlStateManager.disableRescaleNormal();
                break;
            case 3553:
                GlStateManager.disableTexture();
                break;
            case 3089:
                GL11.glDisable(3089);
                break;
        }
    }


    public static void glBlendFunc(int sfactor, int dfactor) {
        GlStateManager.blendFunc(sfactor, dfactor);
    }


    public static void glColor4f(float red, float green, float blue, float alpha) {
        GlStateManager.color4f(red, green, blue, alpha);}


    public static void glDepthMask(boolean flag) {
        GlStateManager.depthMask(flag);
    }


    public static void glNormal3f(float nx, float ny, float nz) {
        GlStateManager.normal3f(nx, ny, nz);
    }


    public static void glPolygonOffset(float factor, float units) {
        GlStateManager.polygonOffset(factor, units);
    }


    public static void glPopMatrix() {
        GlStateManager.popMatrix();
    }

    public static void glPushMatrix() {
        GlStateManager.pushMatrix();
    }

    public static void glRotatef(float angle, float x, float y, float z) {
        GlStateManager.rotatef(angle, x, y, z);
    }

    public static void glScalef(float x, float y, float z) {
        GlStateManager.scalef(x, y, z);
    }

    public static void glTranslatef(float x, float y, float z) {
        GlStateManager.translatef(x, y, z);
    }
}
