import aqs.User;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeTest {
    public static Unsafe UNSAFE = null;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredFields()[0];
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            User user = (User) UNSAFE.allocateInstance(User.class);
            System.out.println(user.getCount());
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
