package jom.com.softserve.s2.task3;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getYears() {
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Student extends Person {
    private String studyPlace;
    private int studyYears;


    public Student(String name, String studyPlace, int studyYears) {
        super(name);
        this.studyPlace = studyPlace;
        this.studyYears = studyYears;
    }

    public String getStudyPlace() {
        return studyPlace;
    }

    public int getStudyYears() {
        return studyYears;
    }

    @Override
    public int getYears() {
        return getStudyYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return studyYears == student.studyYears && Objects.equals(studyPlace, student.studyPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), studyPlace, studyYears);
    }
}

class Worker extends Person {
    private String workPosition;
    private int experienceYears;

    public Worker(String name, String workPosition, int experienceYears) {
        super(name);
        this.workPosition = workPosition;
        this.experienceYears = experienceYears;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    @Override
    public int getYears() {
        return getExperienceYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Worker worker = (Worker) o;
        return experienceYears == worker.experienceYears && Objects.equals(workPosition, worker.workPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), workPosition, experienceYears);
    }
}

public class MyUtils {
    public List<Person> maxDuration(List<Person> people) {
        int studentMax = people.stream()
                .filter(Student.class::isInstance)//Student.class::cast
                .mapToInt(Person::getYears)
                .max().orElse(-1);

        int workerMax = people.stream()
                .filter(Worker.class::isInstance)
                .mapToInt(Person::getYears)
                .max().orElse(-1);

        return people.stream()
                .filter(person -> (person instanceof Student && person.getYears() >= studentMax)
                        || (person instanceof Worker && person.getYears() >= workerMax))
                .collect(Collectors.toList());
    }
}
/*
На рахунок 3 таски
створити 2 ліста один воркери другий студенти
починаєш проходитись по тих персонах що тобі прийшли

а далі
if person instanceof Worker && !workers.contains(person)
тоді в цей воркер ліст додаєш цього воркера
worker.add((Worker)person);
ось так кастиш
і сюди додаєш ще один вкладений іф
але перед цим ще додає дві інтових змінних в які ти будеш записувати
максимальний експіріанс  і макс стаді дюрейшн
щоб ще раз циклами не бігати
і в цьому вкладеному іфі
перевіряєш
якщо ((Worker) person).getExperienceYears() більше цієї твоєї змінної максЕкспіріанс
тоді витягуєш з цього воркера експіріанс і записуєш в цю змінну
Тобто якщо прийшов воркер ти додав його в ліст і там не буде повторень
а також знайшов макс експіріанс
Робиш нище ще один іф для студентів, також там додаєш вкладений іф
І останнє що тобі треба зробити
пробігтись по воркерах і якщо воркера експіріанс рівний максимальному додати його в результуючий список
і так само по студентах
 */
