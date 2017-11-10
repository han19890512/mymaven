package serializable;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SerTest {
    @Test
    public void serializeToDisk() {
        try {
            Person ted = new Person("Ted", "Neward", 39);
            Person charl = new Person("Charlotte","Neward", 38);

            ted.setSpouse(charl);
            charl.setSpouse(ted);

            FileOutputStream fos = new FileOutputStream("c:\\tempdata.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ted);
            oos.close();
        } catch (Exception ex) {
            fail("Exception thrown during test: " + ex.toString());
        }

        try {
            FileInputStream fis = new FileInputStream("c:\\tempdata.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Person ted = (Person) ois.readObject();
            ois.close();

            // 常用断言介绍: 1. assertEquals([String message],Object target,Object result) target与result不相等，
            // 中断测试方法，输出message assertEquals(a, b) 测试a是否等于b（a和b是原始类型数值(primitive value)
            // 或者必须为实现比较而具有equal方法） assertEquals断言两个对象相等，若不满足，方法抛出带有相应信息
            assertEquals(ted.getFirstName(),"Ted");
            assertEquals(ted.getSpouse().getFirstName(),"Charlotte");
            System.out.println(ted.getAge());
            // Clean up the file
           new File("c:\\tempdata.ser").delete();
        } catch (Exception ex) {
            fail("Exception thrown during test: " + ex.toString());
        }
    }
}
