package 深入理解JAVA虚拟机.自动内存管理机制;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出异常
 *
 */

public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
