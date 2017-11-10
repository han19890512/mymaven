package serializable;

import java.io.Serializable;

enum Gender {
    MALE, FEMALE
}

//Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
// JVM会把传来的字节流中的serialVersionUID与本地相应实体（类）的serialVersionUID进行比较，如果相同就认为是一致的，
// 可以进行反序列化，否则就会出现序列化版本不一致的异常。(InvalidCastException)

public class Person2 implements Serializable {

//    显式地定义serialVersionUID有两种用途：
//   1）在某些场合，希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有相同的serialVersionUID；在某些场合，
//      不希望类的不同版本对序列化兼容，因此需要确保类的不同版本具有不同的serialVersionUID。
//   2）当你序列化了一个类实例后，希望更改一个字段或添加一个字段，不设置serialVersionUID，所做的任何更改都将导致无法反序化旧有实例，
//      并在反序列化时抛出一个异常。如果你添加了serialVersionUID，在反序列旧有实例时，
//      新添加或更改的字段值将设为初始化值（对象为null，基本类型为相应的初始默认值），字段被删除将不设置。
    private static final long serialVersionUID = 9057794548370762197L;

    private String firstName;
    private String lastName;
    private int age;
    private Person2 spouse;//配偶
    private Gender gender;

    //writeReplace 和 readResolve 方法使 Person2 类可以将它的所有数据（或其中的核心数据）打包到一个 PersonProxy 中，将它放入到一个流中，然后在反序列化时再进行解包。
    private Object writeReplace()throws java.io.ObjectStreamException{
        return new PersonProxy(this);
    }

    public String toString() {
        return "[Person: firstName=" + firstName +
                " lastName=" + lastName +
                " age=" + age +
                " spouse=" + (spouse != null ? spouse.getFirstName() : "[null]") +
                "]";
    }

    public Person2(String firstName, String lastName, int age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public Person2(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpouse(Person2 spouse) {
        this.spouse = spouse;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Person2 getSpouse() {
        return spouse;
    }
//    注意，PersonProxy 必须跟踪 Person 的所有数据。这通常意味着代理需要是 Person 的一个内部类，以便能访问 private 字段。
//    有时候，代理还需要追踪其他对象引用并手动序列化它们，例如 Person 的 spouse。
//
//    这种技巧是少数几种不需要读/写平衡的技巧之一。例如，一个类被重构成另一种类型后的版本可以提供一个 readResolve 方法，
//    以便静默地将被序列化的对象转换成新类型。类似地，它可以采用 writeReplace 方法将旧类序列化成新版本。
    class PersonProxy implements java.io.Serializable {
        public PersonProxy(Person2 orig) {
            data = orig.getFirstName() + "," + orig.getLastName() + "," + orig.getAge();
            if (orig.getSpouse() != null) {
                Person2 spouse = orig.getSpouse();
                data = data + "," + spouse.getFirstName() + "," + spouse.getLastName() + ","
                        + spouse.getAge();
            }
        }

        public String data;

    /**
     *      一个类被重构成另一种类型后的版本可以提供一个 readResolve 方法，以便将被序列化的对象转换成新类型
     * @return
     * @throws java.io.ObjectStreamException
     */
    private Object readResolve()throws java.io.ObjectStreamException {
            String[] pieces = data.split(",");
            Person2 result = new Person2(pieces[0], pieces[1], Integer.parseInt(pieces[2]));
            if (pieces.length > 3) {
                result.setSpouse(new Person2(pieces[3], pieces[4], Integer.parseInt
                        (pieces[5])));
                result.getSpouse().setSpouse(result);
            }
            return result;
        }
    }
}
