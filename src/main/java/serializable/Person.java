package serializable;

import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;
import java.io.Serializable;

public class Person implements Serializable,ObjectInputValidation {

    private static final long serialVersionUID = 9057794548370762197L;

    private String firstName;
    private String lastName;
    private int age;
    private Person spouse;//配偶


    //  序列化并不安全
    //  我们可以在序列化之前模糊化该数据，将数位循环左移一位，然后在反序列化之后复位。
    //  如果需要对整个对象进行加密和签名，最简单的是将它放在一个 javax.crypto.SealedObject 和java.security.SignedObject 包装器中。
    private void writeObject(java.io.ObjectOutputStream stream)
            throws java.io.IOException {
        // "Encrypt"/obscure the sensitive data
        age = age << 2;
        stream.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws java.io.IOException, ClassNotFoundException {
        stream.defaultReadObject();
        // "Decrypt"/de-obscure the sensitive data
        age = age >> 2;
    }
    //  对于序列化的对象，验证字段，以确保在反序列化之后它们仍具有正确的值，“以防万一”。
    //  为此，可以实现 ObjectInputValidation接口，并覆盖 validateObject() 方法。如果调用该方法时发现某处有错误，
    //  则抛出一个 InvalidObjectException。
    public void validateObject() throws InvalidObjectException {

    }

    public String toString() {
        return "[Person: firstName=" + firstName +
                " lastName=" + lastName +
                " age=" + age +
                " spouse=" + (spouse != null ? spouse.getFirstName() : "[null]") +
                "]";
    }

    public Person(String firstName, String lastName, int age, Person spouse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.spouse = spouse;
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public void setSpouse(Person spouse) {
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

    public Person getSpouse() {
        return spouse;
    }


}
