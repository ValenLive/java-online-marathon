package jom.com.softserve.s3.task6;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;


enum SortOrder{
    ASC, DESC
}
class AddressBook implements Iterable<AddressBook.NameAddressPair> {
    private int counter;
    private NameAddressPair[] addressBook;


    public AddressBook() {

    }

    public AddressBook(int counter) {
        this.counter = 0;
        addressBook = new NameAddressPair[counter];
    }


    public boolean create(String firstName, String lastName, String address) {
        return true;
    }

    public String read(String firstName, String lastName) {
        return "WRONG";
    }

    public boolean update(String firstName, String lastName, String address) {
        return true;
    }

    public boolean delete(String firstName, String lastName){
        return true;
    }

    public int size(){
        return addressBook.length;
    }

    public void sortedBy(SortOrder order){

    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "counter=" + counter +
                ", addressBook=" + Arrays.toString(addressBook) +
                '}';
    }

    @Override
    public Iterator iterator() {
        return new AddressBookIterator();
    }

    private class AddressBookIterator implements Iterator<NameAddressPair> {
        int counter;

        private AddressBookIterator() {
            counter = 0;
        }

        @Override
        public boolean hasNext() {
            return counter < size();
        }

        @Override
        public NameAddressPair next() {
            AddressBook.this.counter++;
            return addressBook[counter++];
        }

    }

    public static class NameAddressPair {
        private Person person;
        private String address;

        public NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        private static class Person {
            private String firstName;
            private String lasName;

            public Person(String firstName, String lasName) {
                this.firstName = firstName;
                this.lasName = lasName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return Objects.equals(firstName, person.firstName) && Objects.equals(lasName, person.lasName);
            }

            @Override
            public int hashCode() {
                return Objects.hash(firstName, lasName);
            }
        }

    }
}