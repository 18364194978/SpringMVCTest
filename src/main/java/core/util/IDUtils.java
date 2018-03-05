package core.util;

import core.util.id.UUIDGenerator;
import java.util.UUID;
/**
 * Created by xie on 2018/3/5.
 */
public class IDUtils {
    private static final UUIDGenerator uuidGenerator = new UUIDGenerator();
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getUUID32(){
        String uuid = getUUID();
        uuid = uuid.replace("-","");
        return uuid;
    }

    /**
     * 根据IP、JVM、时间等生成32位uuid
     * @return
     */
    public static String uuid() {
        return uuidGenerator.create();
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
        System.out.println(getUUID());
        System.out.println(getUUID32());
        System.out.println(getUUID32());
        System.out.println(uuid());
        System.out.println(uuid());
        System.out.println(uuid());
        System.out.println(uuid());
        System.out.println(uuid());
    }
}
