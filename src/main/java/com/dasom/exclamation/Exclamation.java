package com.dasom.exclamation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.lang.reflect.Field;
import java.util.HashMap;
import net.minecraft.client.MinecraftClient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.render.entity.EntityRenderDispatcher;

public class Exclamation {
    MinecraftClient game;
    boolean haveRenderManager = false;
    private boolean voxelEnabled = false;
    public static Exclamation instance;
    private ArrayList<String> newChatLines;
    public RenderExclamation renderPlayerChatBubbles;
    private static final Pattern GET_NUMBER = Pattern.compile("[0-9]+");
    public int idx;
    public boolean toggle_param;
//    public boolean[] toggles = new boolean[] {false,false,false,false,false,false,false};
    public ArrayList<Integer> npc_idxes = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0));
    public int[] npc_id = new int[] {77,74,71,68,65,62,83};

    public Exclamation() {
        this.game = MinecraftClient.getInstance();
        instance = this;
        this.newChatLines = new ArrayList<>();
    }

    public void onTickInGame(MinecraftClient game) {
        if (!this.haveRenderManager)
            synchronized (this.newChatLines) {
                for (String line : this.newChatLines) {
                    if (line.contains("excla_on")) {
                        this.idx = Integer.parseInt(getOnlyNumber(line)) - 231;
                        this.npc_idxes.set(this.idx, this.npc_id[this.idx]);
                    } else if (line.contains("excla_off")) {
                        this.idx = Integer.parseInt(getOnlyNumber(line)) - 231;
                        this.npc_idxes.set(this.idx, 0);
                    }
                    loadRenderManager();
//                    MinecraftClient.getInstance().player.sendChatMessage(String.valueOf(this.npc_id[this.idx]));
                }
                this.newChatLines.clear();
            }
    }

    public String getOnlyNumber(final String str) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = GET_NUMBER.matcher(str);

        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.toString();
    }

    private void loadRenderManager() {
        EntityRenderDispatcher renderManager = MinecraftClient.getInstance().getEntityRenderManager();
        Object skinMapObject = ReflectionUtils.getPrivateFieldValueByType(renderManager, EntityRenderDispatcher.class, Map.class, 1);
        this.renderPlayerChatBubbles = new RenderExclamation(renderManager, this.toggle_param, this.npc_idxes);
        ((HashMap<String, RenderExclamation>) skinMapObject).put("default", this.renderPlayerChatBubbles);
    }

    public static class ReflectionUtils {
        public static Object getPrivateFieldValueByType(Object o, Class<?> objectClasstype, Class<?> fieldClasstype, int index) {
            Class<?> objectClass;
            if (o != null) {
                objectClass = o.getClass();
            } else {
                objectClass = objectClasstype;
            }
            while (!objectClass.equals(objectClasstype) && objectClass.getSuperclass() != null)
                objectClass = objectClass.getSuperclass();
            int counter = 0;
            Field[] fields = objectClass.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if (fieldClasstype.equals(fields[i].getType())) {
                    if (counter == index)
                        try {
                            fields[i].setAccessible(true);
                            return fields[i].get(o);
                        } catch (IllegalAccessException illegalAccessException) {}
                    counter++;
                }
            }
            return null;
        }
    }
    public void clientString (String var1){
        if (!this.voxelEnabled)
            synchronized (this.newChatLines) {
                this.newChatLines.add(var1);
            }
    }
}
