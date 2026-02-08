package utils.keyMapper;

import io.appium.java_client.android.nativekey.AndroidKey;

import java.util.HashMap;

public class KeyMapper {
    private KeyMapper() {
    }

    public static AndroidKey mapper(String key) {
        HashMap<String, AndroidKey> keyMap = new HashMap<>();
        keyMap.put("0", AndroidKey.DIGIT_0);
        keyMap.put("1", AndroidKey.DIGIT_1);
        keyMap.put("2", AndroidKey.DIGIT_2);
        keyMap.put("3", AndroidKey.DIGIT_3);
        keyMap.put("4", AndroidKey.DIGIT_4);
        keyMap.put("5", AndroidKey.DIGIT_5);
        keyMap.put("6", AndroidKey.DIGIT_6);
        keyMap.put("7", AndroidKey.DIGIT_7);
        keyMap.put("8", AndroidKey.DIGIT_8);
        keyMap.put("9", AndroidKey.DIGIT_9);
        keyMap.put("del", AndroidKey.DEL);

        return keyMap.get(key);
    }
}
